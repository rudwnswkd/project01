package com.javalab.sec02.school;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Scanner;

public class SchoolManagementEx {
    private static DataRepository repo = new DataRepository();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=============================");
            System.out.println("학사 행정관리 프로그램");
            System.out.println("=============================");
            System.out.println("1. 학생관련 업무");
            System.out.println("2. 교수 관련 업무");
            System.out.println("3. 학과 관련 업무");
            System.out.println("4. 성적관련 업무");
            System.out.println("5. 종료");
            System.out.println("=============================");
            System.out.print("메뉴 선택: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    studentMenu();
                    break;
                case 2:
                    professorMenu(); // 교수
                    break;
                case 3:
                    departmentMenu(); // 학과
                    break;
                case 4:
                    gradesMenu(); // 성적
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    } // end of main

    private static void studentMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=============================");
            System.out.println("1. 학생 등록");
            System.out.println("2. 학생 조회");
            System.out.println("3. 학생 정보 수정");
            System.out.println("4. 학생 정보 삭제");
            System.out.println("5. 메인 메뉴로 가기");
            System.out.println("=============================");
            System.out.print("메뉴 선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1:
                    registerStudent(scanner);
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    updateStudent(scanner);
                    break;
                case 4:
                    deleteStudent(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    private static void registerStudent(Scanner scanner) {
        System.out.println("학생 ID: ");
        String id = scanner.nextLine();
        System.out.println("주민번호: ");
        String jumin = scanner.nextLine();
        System.out.println("이름: ");
        String name = scanner.nextLine();
        System.out.println("학년: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("주소: ");
        String address = scanner.nextLine();
        System.out.println("학과 코드: ");
        int department = scanner.nextInt();
        scanner.nextLine();

        Student student = new Student(id, jumin, name, year, address, department);
        repo.getStudents().add(student);
        System.out.println("학생이 성공적으로 등록되었습니다.");
    }

    private static void displayStudents() {
        System.out.println("등록된 학생 목록:");
        for (Student s : repo.getStudents()) {
            System.out.println(s.getId() + " " + s.getName() + " " + s.getYear() + " " + s.getAddress() + " " + s.getDepartment());
        }
    }

    private static void updateStudent(Scanner scanner) {
        System.out.println("수정할 학생의 ID를 입력하세요: ");
        String id = scanner.nextLine();
        for (Student s : repo.getStudents()) {
            if (s.getId().equals(id)) {
                System.out.println("새 주민번호: ");
                String jumin = scanner.nextLine();
                System.out.println("새 이름: ");
                String name = scanner.nextLine();
                System.out.println("새 학년: ");
                int year = scanner.nextInt();
                scanner.nextLine();
                System.out.println("새 주소: ");
                String address = scanner.nextLine();
                System.out.println("새 학과 코드: ");
                int department = scanner.nextInt();
                scanner.nextLine();

                s.setJumin(jumin);
                s.setName(name);
                s.setYear(year);
                s.setAddress(address);
                s.setDepartment(department);
                System.out.println("학생 정보가 업데이트 되었습니다.");
                return;
            }
        }
        System.out.println("해당 ID의 학생을 찾을 수 없습니다.");
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.println("삭제할 학생의 ID를 입력하세요: ");
        String id = scanner.nextLine();
        for (int i = 0; i < repo.getStudents().size(); i++) {
            if (repo.getStudents().get(i).getId().equals(id)) {
                repo.getStudents().remove(i);
                System.out.println("학생 정보가 삭제되었습니다.");
                return;
            }
        }
        System.out.println("해당 ID의 학생을 찾을 수 없습니다.");
    }

    private static void  departmentMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=============================");
            System.out.println("1. 학과 조회");
            System.out.println("2. 학과 교수 조회");
            System.out.println("3. 학과별 학생 목록");
            System.out.println("4. 학과 정보 조회");
            System.out.println("5. 메인 메뉴로 가기");
            System.out.println("=============================");
            System.out.print("메뉴 선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1:
                    displayDepartment();
                    break;
                case 2:
                    displayProfessorsByDepartment();
                    break;
                case 3:
                    displayStudentsByDepartment();
                    break;
                case 4:
                    displayDepartmentInfo();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    private static void professorMenu() { // 교수 관리 메뉴
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("==================");
            System.out.println("1. 교수 등록");
            System.out.println("2. 교수 조회");
            System.out.println("3. 교수 정보 수정");
            System.out.println("4. 교수 정보 삭제");
            System.out.println("5. 학과별 교수 목록");
            System.out.println("6. 직급별 교수 목록");
            System.out.println("7. 메인 메뉴로 가기");
            System.out.println("메뉴 선택");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 지우기

            switch (choice) {
                case 1:
                    professorRegistration(scanner); // 교수 등록
                    break;
                case 2:
                    professorCheck(); // 교수 조회
                    break;
                case 3:
                    professorModify(scanner); // 교수 정보 수정
                    break;
                case 4:
                    professorDelete(scanner); // 교수 정보 삭제
                    break;
                case 5:
                    ProfessorListByDepartment(scanner); // 학과별 교수 목록
                    break;
                case 6:
                    ListofProfessorsByRank(scanner); // 직급별 교수 목록
                    break;
                case 7:
                    return; // 메뉴로 돌아가기
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요");
            }
        }
    } // end of main

    // 학과 메서드
    private static void displayDepartment() {
        System.out.println("학과 목록");
        for (Department d : repo.getDepartments()) {
            System.out.println(d.getId() + " " + d.getName() + " " + d.getOffice());
        }
    }

    private static void displayProfessorsByDepartment() {
        Scanner scanner = new Scanner(System.in);
        boolean validDepartmentId = false;
        Map<Integer, Integer> departmentMap = new HashMap<>();
        departmentMap.put(1, 920); // 예시: 1번은 학과 ID 920번을 나타냄
        departmentMap.put(2, 923);
        departmentMap.put(3, 925);

        while (true) {
            System.out.print("학과 번호를 입력하세요 : \n");
            System.out.println("===============");
            System.out.println("1. 컴퓨터 공학과\n2. 산업공학과\n3. 전자공학과\n4. 메인으로 돌아가기");
            System.out.println("===============");
            int departmentNumber = scanner.nextInt();

            if (departmentNumber == 4) {
                break; // 4를 입력하면 반복문 종료하여 메인으로 돌아감
            }

            if (departmentMap.containsKey(departmentNumber)) {
                int departmentId = departmentMap.get(departmentNumber);
                boolean found = false;
                for (Professor professor : repo.getProfessors()) {
                    if (professor.getDepartmentId() == departmentId) {
                        System.out.println(professor.getId() + " " + professor.getName() + " " + professor.getHireDate() + " " + professor.getGrade());
                        found = true;
                    }
                }
                if (found) {
                    validDepartmentId = true;
                } else {
                    System.out.println("해당 학과에 속한 교수를 찾을 수 없습니다.");
                }
            } else {
                System.out.println("올바른 학과 번호를 입력해주세요.");
            }
        }
    }

    private static void displayStudentsByDepartment() {
        Scanner scanner = new Scanner(System.in);
        boolean validDepartmentId = false;
        Map<Integer, Integer> departmentMap = new HashMap<>();
        departmentMap.put(1, 920); // 예시: 1번은 학과 ID 920번을 나타냄
        departmentMap.put(2, 923);
        departmentMap.put(3, 925);

        while (true) {
            System.out.print("학과 번호를 입력하세요 : \n");
            System.out.println("===============");
            System.out.println("1. 컴퓨터 공학과\n2. 산업공학과\n3. 전자공학과\n4. 메인으로 돌아가기");
            System.out.println("===============");
            int departmentNumber = scanner.nextInt();

            if (departmentNumber == 4) {
                break; // 4를 입력하면 반복문 종료하여 메인으로 돌아감
            }

            if (departmentMap.containsKey(departmentNumber)) {
                int departmentId = departmentMap.get(departmentNumber);
                boolean found = false;
                for (Student s : repo.getStudents()) {
                    if (s.getDepartmentId() == departmentId) {
                        System.out.println(s.getId() + " " + s.getName());
                        found = true;
                    }
                }
                if (found) {
                    validDepartmentId = true;
                    break; // 결과를 찾았으므로 반복문 종료
                } else {
                    System.out.println("해당 학과에 속한 학생을 찾을 수 없습니다. 다시 입력해주세요.");
                }
            } else {
                System.out.println("올바른 학과 번호를 입력해주세요.");
            }
        }
    }



    private static void displayDepartmentInfo() {
        Scanner scanner = new Scanner(System.in);
        boolean validDepartmentId = false;

        while (!validDepartmentId) {
            System.out.print("학과 ID를 입력하세요: ");
            int departmentId = scanner.nextInt();

            for (Department department : repo.getDepartments()) {
                if (department.getId() == departmentId) {
                    System.out.println("학과 이름: " + department.getName());
                    System.out.println("사무실 위치: " + department.getOffice());
                    validDepartmentId = true;
                }
            }

            if (!validDepartmentId) {
                System.out.println("해당 학과를 찾을 수 없습니다. 다시 입력해주세요.");
            }
        }
    }

        private  static void professorRegistration(Scanner scanner) {
            System.out.print("교수ID: ");
            String id = scanner.nextLine();
            System.out.print("주민번호: ");
            String jumin = scanner.nextLine();
            System.out.print("이름: ");
            String name = scanner.nextLine();
            System.out.print("학과코드: ");
            int department = scanner.nextInt();
            scanner.nextLine();
            System.out.print("직급: ");
            String rank = scanner.nextLine();
            System.out.print("고용시기: ");
            String hiredate = scanner.nextLine();

            Professor professor = new Professor(id, jumin, name, department, rank,hiredate);
            repo.getProfessors().add(professor);
            System.out.println("새로운 교수가 등록되었습니다.");
        } // end of professorRegistration

        private static void professorCheck() {
            System.out.println("등록된 교수 목록");
            for(Professor p : repo.getProfessors()){
                System.out.println(p.getId() + " " + p.getJumin() + " " + p.getName() + " " + p.getDepartment()
                        + " " + p.getGrade() + " " + p.getHireDate());
            }
        } //end of professorCheck

        private  static void professorModify(Scanner scanner) {
            System.out.println("수정할 교수의 ID를 입력하세요");
            String id = scanner.nextLine();
            for(Professor p : repo.getProfessors()){
                if(p.getId().equals(id)){
                    System.out.print("새 주민번호: ");
                    String jumin = scanner.nextLine();
                    System.out.print("새 이름: ");
                    String name = scanner.nextLine();
                    System.out.print("새 학과코드: ");
                    int depertment = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("새 직급: ");
                    String rank = scanner.nextLine();
                    System.out.print("새 고용시기: ");
                    String hiredate = scanner.nextLine();

                    p.setJumin(jumin);
                    p.setName(name);
                    p.setDepartment(depertment);
                    p.setGrade(rank);
                    p.setHiredate(hiredate);
                    System.out.println("교수 정보가 새로 바꼈습니다.");
                    return;
                }
            }
            System.out.println("해당 ID의 교수가 없습니다.");
        } // end of professorModify

        private static void professorDelete(Scanner scanner){
            System.out.println("삭제할 교수ID를 입력하세요");
            String id = scanner.nextLine();
            for(int i = 0; i < repo.getProfessors().size(); i++) {
                if(repo.getProfessors().get(i).getId().equals(id)) {
                    repo.getProfessors().remove(i);
                    System.out.println("교수 정보가 삭제되었습니다.");
                    return;
                }
            }
            System.out.println("해당 교수ID가 없습니다.");
        } // end of professorDelete

        private static void ProfessorListByDepartment(Scanner scanner){
            System.out.println("===============");
            System.out.println("1. 컴퓨터공학과");
            System.out.println("2. 산업공학과");
            System.out.println("3. 전자공학과");
            System.out.println("===============");
            System.out.println("학과를 선택해주세요");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 지우기

            switch (choice){
                case 1:
                    for(Professor p : repo.getProfessors()) {
                        if(p.getDepartment() == 920) {
                            System.out.println(p.getId() + " " + p.getJumin() + " " + p.getName() + " " + p.getDepartment()
                                    + " " + p.getGrade() + " " + p.getHireDate());
                        }
                    }
                    break;
                case 2:
                    for(Professor p : repo.getProfessors()){
                        if(p.getDepartment() == 923){
                            System.out.println(p.getId() + " " + p.getJumin() + " " + p.getName() + " " + p.getDepartment()
                                    + " " + p.getGrade() + " " + p.getHireDate());
                        }
                    }
                    break;
                case 3:
                    for(Professor p : repo.getProfessors()){
                        if(p.getDepartment() == 925){
                            System.out.println(p.getId() + " " + p.getJumin() + " " + p.getName() + " " + p.getDepartment()
                                    + " " + p.getGrade() + " " + p.getHireDate());
                        }
                    }
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요");
            }
        }// end of ProfessorListByDepartment

        private static void ListofProfessorsByRank(Scanner scanner) {
            System.out.println("==============");
            System.out.println("1. 교수");
            System.out.println("2. 부교수");
            System.out.println("3. 조교수");
            System.out.println("==============");
            System.out.println("직급을 선택하세요");
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    for(Professor p : repo.getProfessors()){
                        if(p.getGrade().equals("교수")){
                            System.out.println(p.getId() + " " + p.getJumin() + " " + p.getName() + " " + p.getDepartment()
                                    + " " + p.getGrade() + " " + p.getHireDate());
                        }
                    }
                    break;
                case 2:
                    for(Professor p : repo.getProfessors()){
                        if(p.getGrade().equals("부교수")){
                            System.out.println(p.getId() + " " + p.getJumin() + " " + p.getName() + " " + p.getDepartment()
                                    + " " + p.getGrade() + " " + p.getHireDate());
                        }
                    }
                    break;
                case 3:
                    for(Professor p : repo.getProfessors()){
                        if(p.getGrade().equals("조교수")){
                            System.out.println(p.getId() + " " + p.getJumin() + " " + p.getName() + " " + p.getDepartment()
                                    + " " + p.getGrade() + " " + p.getHireDate());
                        }
                    }
                    break;
                default:
                    System.out.println("잘못된 직급을 선택했습니다.");
            }
        } // end of ListofProfessorsByRank

    private static void gradesMenu () {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=============================");
            System.out.println("1. 성적 등록");
            System.out.println("2. 성적 조회");
            System.out.println("3. 메인 메뉴로 가기");
            System.out.println("=============================");
            System.out.print("메뉴 선택 : ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1:

                    registerGrade(scanner);
                    break;
                case 2:
                    displayGradesByStudentId(scanner);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    // 성적 등록
    private static void registerGrade (Scanner scanner){
        System.out.println("학번: ");
        String studentId = scanner.nextLine();
        System.out.println("학과코드: ");
        String subjectCode = scanner.nextLine();
        System.out.println("성적: ");
        String score = scanner.nextLine();

        Takes takes = new Takes(studentId, subjectCode, score);
        repo.getTakes().add(takes);
        System.out.println("성적이 성공적으로 등록되었습니다.");
    }

    // 성적 조회
    private static void displayGradesByStudentId (Scanner scanner){
        System.out.println("조회할 학생의 학번을 입력하세요: ");
        String studentId = scanner.nextLine();
        boolean found = false;
        for (Takes takes : repo.getTakes()) {
            if (takes.getId().equals(studentId)) {
                System.out.println("학번: " + takes.getId() + ",학과코드 : " + takes.getSubject() + ", 성적: " + takes.getScore());
                found = true;
            }
        }
        if (!found) {
            System.out.println("해당 학번의 학생의 성적을 찾을 수 없습니다.");
        }
    }
}   // end of class SchoolManagementEx