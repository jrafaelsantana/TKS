package br.ufrpe.tks.gui.model;

import br.ufrpe.tks.dados.IRepositorioPessoa;
import br.ufrpe.tks.dados.RepositorioPessoa;
import br.ufrpe.tks.exceptions.UsuarioNaoEncontradoException;
import br.ufrpe.tks.gui.MainApp;
import br.ufrpe.tks.negocios.CadastroPessoa;
import br.ufrpe.tks.negocios.Servidor;
import br.ufrpe.tks.negocios.beans.Administrador;
import br.ufrpe.tks.negocios.beans.Funcionario;
import br.ufrpe.tks.negocios.beans.Pessoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdministradorController {
	@FXML
	private Label lbNome;
	@FXML
	private Label lbAviso;
	@FXML
	private TextField campoNome;
	@FXML
	private TextField campoMatricula;
	@FXML
	private PasswordField campoSenha;
	@FXML
	private ComboBox selectCargo;
	@FXML
	private ComboBox selectSexo;
	@FXML
	private ComboBox selectMotorista;
	@FXML
	private ComboBox selectNivel;
	@FXML
	private Button btCadastrar;
	@FXML
	private Button btLogout;
	@FXML
	private Button btEditarUsuario;
	@FXML
	private Button btApagarUsuario;
	@FXML
	private Label lbAviso2;
	@FXML
	private Label lbAviso3;

	@FXML
	private TableView<Pessoa> tableEditar;
	@FXML
	private TableView<Pessoa> tableApagar;
	@FXML
	private TableColumn<Pessoa, String> colunaMatricula;
	@FXML
	private TableColumn<Pessoa, String> colunaNome;
	@FXML
	private TableColumn<Pessoa, String> colunaMatricula2;
	@FXML
	private TableColumn<Pessoa, String> colunaNome2;

	private Administrador logado;
	private Stage administradorStage;
	private MainApp mainApp;

	IRepositorioPessoa repositorioPessoa = RepositorioPessoa.getInstance();
	CadastroPessoa cadastroPessoa = new CadastroPessoa(repositorioPessoa);

	@FXML
	private void initialize() {
		colunaMatricula.setCellValueFactory(cellData -> cellData.getValue().matriculaProperty());
		colunaNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colunaMatricula2.setCellValueFactory(cellData -> cellData.getValue().matriculaProperty());
		colunaNome2.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
	}

	public void setAdministradorStage(Stage administradorStage) {
		this.administradorStage = administradorStage;
	}

	public void setPessoa(Pessoa pessoa) {
		this.logado = (Administrador) pessoa;
		selectMotorista.getItems().addAll("Sim", "Não");
		selectSexo.getItems().addAll('M', 'F');
		selectNivel.getItems().addAll("Funcionário", "Administrador");
		selectCargo.getItems().addAll("Soldado", "Cabo", "Sargento", "Sub-tenente", "Tenente", "Capitão", "Major",
				"Tenente Coronel", "Coronel");
		lbNome.setText(logado.getNome());
	}

	@FXML
	private void handleCadastrar() {
		try {
			if (!campoNome.getText().equals("") || !campoMatricula.getText().equals("")
					|| !campoSenha.getText().equals("")) {
				try {
					cadastroPessoa.procurar(campoMatricula.getText());
					lbAviso.setText("Número da matrícula já utilizado.");
				} catch (UsuarioNaoEncontradoException e) {
					boolean motorista = false;

					if (selectMotorista.getValue() == "Sim") {
						motorista = true;
					} else if (selectMotorista.getValue() == "Não") {
						motorista = false;
					}

					if ((String) selectNivel.getValue().toString() == "Funcionário") {
						try {
							cadastroPessoa.cadastrarFuncionario(campoNome.getText(), (String) selectCargo.getValue(),
									(char) selectSexo.getValue(), campoMatricula.getText(), motorista,
									campoSenha.getText());
							lbAviso.setText("Funcionário cadastrado");
							this.atualizarTabelas();
						} catch (Exception x) {
							lbAviso.setText("Ocorreu um erro.");
						}
					} else {
						try {
							cadastroPessoa.cadastrarAdministrador(campoNome.getText(), (char) selectSexo.getValue(),
									campoMatricula.getText(), campoSenha.getText());
							lbAviso.setText("Administrador cadastrado");
							this.atualizarTabelas();
						} catch (Exception x) {
							lbAviso.setText("Ocorreu um erro.");
						}
					}
				}
			} else {
				lbAviso.setText("Preencha todos os campos.");
			}
		} catch (Exception e) {
			lbAviso.setText("Ocorreu um erro. Tente novamente.");
			e.printStackTrace();
		}
	}

	@FXML
	private void handleNivel() {
		if ((String) selectNivel.getValue().toString() == "Administrador") {
			selectCargo.setDisable(true);
			selectMotorista.setDisable(true);
		} else {
			selectCargo.setDisable(false);
			selectMotorista.setDisable(false);
		}
	}

	@FXML
	private void handleEditarUsuario() {
		Pessoa selectedPerson = tableEditar.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = mainApp.showEditarPessoaDialog(selectedPerson);
			if (okClicked) {
				this.atualizarTabelas();
			}

		} else {
			lbAviso2.setText("Nenhum usuário selecionado");
		}
	}

	@FXML
	private void handleApagarUsuario() {
		Pessoa selectedPerson = tableApagar.getSelectionModel().getSelectedItem();
		if (selectedPerson.equals(logado)) {
			lbAviso3.setText("Impossível apagar esse usuário");
		} else {
			if (selectedPerson != null) {
				try {
					lbAviso3.setText("");
					Servidor.getInstance().remover(selectedPerson.getMatricula());
					this.atualizarTabelas();
				} catch (UsuarioNaoEncontradoException e) {
					lbAviso3.setText("Erro");
					e.printStackTrace();
				}

			} else {
				lbAviso3.setText("Nenhum usuário selecionado");
			}
		}
	}

	@FXML
	private void handleLogout() {
		mainApp.showLogin();
	}

	public void atualizarTabelas() {
		tableApagar.setItems(this.getpersonData());
		tableEditar.setItems(getpersonData());
	}

	private ObservableList<Pessoa> getpersonData() {
		return FXCollections.observableArrayList(repositorioPessoa.getUsuarios());
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		this.atualizarTabelas();
	}
}