# TenQuest-BE
Repository for TenQuest service

# ERD
```mermaid
erDiagram
  member_table{
        VARCHAR member_id PK "회원 식별자"
        VARCHAR user_id "아이디"
        VARCHAR user_info "비밀번호"
        VARCHAR user_name "이름"
        VARCHAR user_email "이메일"
        VARCHAR user_roles "권한"
    }

  member_table || -- o{ template_table : "Member(1):Template(0..N)"
    
	template_table{
      VARCHAR template_id PK "템플릿 식별자"
			VARCHAR teamplate_owner FK "소유자의 회원 식별자"
			VARCHAR template_name "템플릿명"
			TIMESTAMP created_at "생성 시간" 
			TINYINT is_public "공개 여부"
}
  template_doc_table || -- |{ template_table : "TemplateDoc(N):Template(1)"
	template_doc_table{
		INT template_doc_id PK "템플릿 도큐먼트 식별자"
		VARCHAR template_id FK "템플릿 식별자"
		VARCHAR question_id FK "질문 식별자"
		INT question_order "질문 순서" 
	}
	
	preset_table{
		VARCHAR preset_id PK "프리셋 식별자"
		VARCHAR preset_name "프리셋명"
	}

	preset_doc_table }| -- || preset_table : "PresetDoc(N):Preset(1)"
	%% `PresetDoc`은 하나의 Preset을 이루는 항목이다.
	preset_doc_table{
		VARCHAR preset_doc_id PK "프리셋 도큐먼트 식별자"
		VARCHAR	preset_id FK "도큐먼트가 속한 프리셋 식별자"
		VARCHAR	question_id "프리셋 도큐먼트에 대한 질문 식별자"
		INT question_order "질문 순서"
	}

	replyer_table{
		VARCHAR replyer_id PK "답변자의 식별자"
		VARCHAR replyer_name FK "답변자의 닉네임"
	}


	answer_table || -- || replyer_table : "Answer(1):Replyer(1)"
	answer_table || -- || template_doc_table : "Answer(1):TemplateDoc(1)"
	answer_table{
		VARCHAR answer_id PK "답변 식별자"
		VARCHAR replyer_id FK "답변자 식별자"
		INT doc_id FK "답변한 템플릿 도큐먼트 식별자"
		VARCHAR answer_content "답변 식별자"
		TINYINT is_public "답변 공개 여부"
		TIMESTAMP answer_time "답변 생성 시간"
	}


	question_table }o -- || member_table : "Question(0..N):Member(1)"
	
	question_table{
		VARCHAR question_id PK "질문 식별자"
		VARCHAR question_content "내용"
		INT question_category_id "질문 카테고리"
		VARCHAR question_created_by FK "질문을 생성한 회원 식별자"
	}

```

# Updates 
**23.03.17** - [Response 관련 업데이트 내용](https://github.com/TenQuest-Team/TenQuest-BE/blob/develop/updates/230317%20-%20Response%20%EA%B4%80%EB%A0%A8%20%EC%97%85%EB%8D%B0%EC%9D%B4%ED%8A%B8%20%EB%82%B4%EC%9A%A9.md)
