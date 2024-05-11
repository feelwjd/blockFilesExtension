# 서버 구성
서버 : Spring Boot
DataBase : Mysql | AWS RDS
인스턴스 : AWS EC2

# API 요약
## Endpoints
### 1. 확장자 추가
- URL: POST /task/extension/add
- 파라미터: HttpSession session, RequestBody Object object
### 2. 해당 세션의 확장자 히스토리 추가
- URL: POST /task/extension/addHistory
- 파라미터: HttpSession session, RequestBody Object object
### 3. 지정된 세션에 대한 확장자 히스토리 검색
- URL: GET /task/extension/history
- 파라미터: HttpSession session
### 4. 모든 확장자 검색 
- URL: GET /task/extension/all
- 파라미터: HttpSession session
### 5. 해당 세션의 확장자 체크 상태 응답
- URL: POST /task/extension/check
- 파라미터: HttpSession session, RequestBody Object object
### 6. 해당 세션에서 확장자 삭제 시 확장자 히스토리 삭제
- URL: POST /task/extension/deleteHistory
- 파라미터: HttpSession session, RequestBody Object object
### 7. 세션 기준의 상위 선택 확장자 검색
- URL: GET /task/extension/top
- 파라미터: HttpSession session.
### 8. 파일 업로드
- URL: POST /file/uploadFile
- 파라미터: HttpSession session, MultipartFile file

# 테이블 구조
# BLOCK_EXTENSION Schema

## Table: BLOCK_FILE_EXTENSION_HISTORY
| Column           | Type       |
| ---------------- | ---------- |
| history_index    | int        |
| session_id       | varchar(50)|
| extension_index  | int        |
| create_date      | datetime   |
| update_date      | datetime   |
| check_yn         | varchar(1) |
| del_yn           | varchar(1) |

## Table: BLOCK_FILE_EXTENSION_MASTER
| Column           | Type         |
| ---------------- | ------------ |
| extension_index  | int          |
| extension_name   | varchar(21)  |
| create_date      | datetime     |
| update_date      | datetime     |
| select_count     | int          |


