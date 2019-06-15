package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import controller.MemberService;
import controller.MemberServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Member;

public class MemberViewController implements Initializable {
	
	Calendar oCalendar = Calendar.getInstance( );

	@FXML	private Button btnCreate;
	@FXML	private Button btnUpdate;
	@FXML	private Button btnDelete;
	
	@FXML	private Button btnAddress;
	@FXML	private Button btnName;

	@FXML	private TextArea taExecute;
	@FXML	private TextField tfExecute;
	
	@FXML	private TextField tfEmail;
	@FXML	private PasswordField tfPW;
	@FXML	private TextField tfName;
	@FXML	private TextField tfContact;
	@FXML	private TextField tfBirth;
	@FXML	private TextField tfAddress;

	
	@FXML 	private TableView<Member> tableViewMember;
	@FXML	private TableColumn<Member, String> columnName;
	@FXML	private TableColumn<Member, String> columnEmail;
	@FXML	private TableColumn<Member, String> columnPW;
	@FXML	private TableColumn<Member, String> columnBirth;
	@FXML	private TableColumn<Member, String> columnAge;
	@FXML	private TableColumn<Member, String> columnAddress;
	@FXML	private TableColumn<Member, String> columnContact;
	
	// Member : model이라고도 하고 DTO, VO 라고도 함
	// 시스템 밖에 저장된 정보를 객체들간에 사용하는 정보로 변환한 자료구조 또는 객체
	private final ObservableList<Member> data = FXCollections.observableArrayList();
	// 목록 : 이중연결리스트는 아니지만 리스트의 특징과 배열 특징을 잘 혼용해 놓은 클래스 ArrayList 
	ArrayList<Member> memberList;
	MemberService memberService;
	
	public MemberViewController() {
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		memberService = new MemberServiceImpl();
		// 람다식 : java 8  함수형 언어 지원 
		columnEmail.setCellValueFactory(cvf -> cvf.getValue().uemailProperty());
		columnPW.setCellValueFactory(cvf -> cvf.getValue().upwProperty());
		columnName.setCellValueFactory(cvf -> cvf.getValue().unameProperty());	
		columnBirth.setCellValueFactory(cvf -> cvf.getValue().ubirthProperty());	
		columnAge.setCellValueFactory(cvf -> cvf.getValue().uageProperty());	
		columnAddress.setCellValueFactory(cvf -> cvf.getValue().uaddressProperty());	
		columnContact.setCellValueFactory(cvf -> cvf.getValue().ucontactProperty());
		
		tableViewMember.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showMemberInfo(newValue));

		//btnCreate.setOnMouseClicked(event -> handleCreate());		
		// btnDelete.setOnMouseClicked(e -> handleDelete());		
		//btnExecute.setOnMouseClicked(event -> handleExecute());	
		btnAddress.setOnMouseClicked(event -> handleAddress());		

		
		
		loadMemberTableView();
	}
	String str = ""; // 인스턴스 변수 - 객체 변수, 객체가 존재하는 동안 메모리에 존재
	@FXML 
	private void handleExecute() { // event source, listener, handler
		str = str + tfExecute.getText() + "\n";
		//str = ts.setTextArea(tfExecute.getText());
		/*
		str = taExecute.getText();
		String name = tfExecute.getText();
		str = str + ts.appendTextArea(name);
		*/
		taExecute.setText(str);
	}
	
	private void showMemberInfo(Member member) {
		if (member != null) {
			tfEmail.setText(member.getUemail());
			tfPW.setText(member.getUpw());
			tfName.setText(member.getUname());
			tfBirth.setText(member.getUbirth());
			tfAddress.setText(member.getUaddress());
			tfContact.setText(member.getUcontact());

//			tfMobilePhone.setText(member.getMobilePhone());
		}
		 else {
			 tfEmail.setText("");
			 tfPW.setText("");
		     tfName.setText("");
		     tfBirth.setText("");
		     tfAddress.setText("");
		     tfContact.setText("");

//		     tfMobilePhone.setText("010");
		 }
	}
	
	private void loadMemberTableView() {
		memberList = memberService.readList();
		for(Member m : memberList) {
			data.add(m);
		}
		tableViewMember.setItems(data);
	}
	
	
	@FXML 
	private void handleAddress() {
		String condition = tfExecute.getText();
		taExecute.setText("");
		if(condition.length() > 0) {
			List<Member> searched = memberService.Address(condition);
			if(searched.size() > 0) {
				int no = 1;
				for(Member m : searched) {
					taExecute.appendText(no++ + " ) " + m.getUaddress() + " : " + m.getUemail() + " : " + m.getUname() + " \n");
				}
			}
			else
				taExecute.setText("검색 조건에 맞는 정보가 없습니다.");
		}
		else
			this.showAlert("검색 조건을 입력하십시요");			
	}
	
	@FXML 
	private void handleName() {
		String condition = tfExecute.getText();
		taExecute.setText("");
		if(condition.length() > 0) {
			List<Member> searched = memberService.Name(condition);
			if(searched.size() > 0) {
				int no = 1;
				for(Member m : searched) {
					taExecute.appendText(no++ + " ) " + m.getUaddress() + " : " + m.getUemail() + " : " + m.getUname() + " \n");
				}
			}
			else
				taExecute.setText("검색 조건에 맞는 정보가 없습니다.");
		}
		else
			this.showAlert("검색 조건을 입력하십시요");			
	}
	
	
	@FXML 
	private void handleCreate() { // event source, listener, handler
		if(tfEmail.getText().length() > 0) {
			
		
			String a = tfBirth.getText();
			int birth = Integer.parseInt(a.substring(0,4));

			int today = oCalendar.get(Calendar.YEAR);
			int age = today - birth + 1;
			String age2 = age + "";
			
			
			Member newMember = 
					new Member(tfEmail.getText(), tfPW.getText(), tfName.getText(), tfBirth.getText(), age2, tfAddress.getText(), tfContact.getText());
			if( memberService.findByUid(newMember) < 0) {
				data.add(newMember);			
				tableViewMember.setItems(data);
				memberService.create(newMember);
			}
			else {
				showAlert("아이디 중복으로 등록할 수 없습니다.");
			}
			
		} else
			showAlert("ID 입력오류");
	}
	@FXML 
	private void handleUpdate() {
		
		String a = tfBirth.getText();
		int birth = Integer.parseInt(a.substring(0,4));

		int today = oCalendar.get(Calendar.YEAR);
		int age = today - birth + 1;
		String age2 = age + "";
		Member newMember = new Member(tfEmail.getText(), tfPW.getText(), tfName.getText(), tfBirth.getText(), age2, tfAddress.getText(), tfContact.getText());

		int selectedIndex = tableViewMember.getSelectionModel().getSelectedIndex();
		// uid를 변경하고 수정 -> 생성으로 처리하게 된다.
		// uid로 조회하는데 uid가 수정이되면 실제로 수정이 불가능함, findByUid() 가 -1 반환
		if (selectedIndex != memberService.findByUid(newMember)) {
			showAlert("아이디를 수정하면 업데이트 할 수 없습니다.");    
		}
		else if (selectedIndex >= 0) {
			tableViewMember.getItems().set(selectedIndex, newMember);
			memberService.update(newMember);			
		} else {
			showAlert("업데이트할 수 없습니다.");          
        }
	}
	
	@FXML 
	private void handleDelete() {
		int selectedIndex = tableViewMember.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			memberService.delete(tableViewMember.getItems().remove(selectedIndex));			
		} else {
			showAlert("삭제 대상이 없습니다.");
        }
	}
	
	private void showAlert(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(mainApp.getRootStage());
        alert.setTitle("알림");
        alert.setContentText("확인 : " + message);            
        alert.showAndWait();
	}

	private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

}
