package vo;

import java.sql.Date;

/*
  
	CREATE DATABASE subscribeproject;
	
	CREATE TABLE qna (
		qna_idx INT PRIMARY KEY,
		qna_subject VARCHAR(50),
		qna_content VARCHAR(5000),
		qna_file VARCHAR(50) NOT NULL,
   		qna_real_file VARCHAR(50) NOT NULL,
		qna_re_ref INT NOT NULL,
		qna_re_lev INT NOT NULL,
		qna_re_seq INT NOT NULL,
		qna_date DATE
	);

*/


public class QnaDTO {
	
	private int qna_idx;
	private String qna_subject;
	private String qna_content;
	private String qna_file;
	private String qna_real_file;
	private int qna_re_ref;
	private int qna_re_lev;
	private int qna_re_seq;
	private Date qna_date;
	
	
	public int getQna_idx() {
		return qna_idx;
	}
	public void setQna_idx(int qna_idx) {
		this.qna_idx = qna_idx;
	}
	public String getQna_subject() {
		return qna_subject;
	}
	public void setQna_subject(String qna_subject) {
		this.qna_subject = qna_subject;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public String getQna_file() {
		return qna_file;
	}
	public void setQna_file(String qna_file) {
		this.qna_file = qna_file;
	}
	public String getQna_real_file() {
		return qna_real_file;
	}
	public void setQna_real_file(String qna_real_file) {
		this.qna_real_file = qna_real_file;
	}
	public int getQna_re_ref() {
		return qna_re_ref;
	}
	public void setQna_re_ref(int qna_re_ref) {
		this.qna_re_ref = qna_re_ref;
	}
	public int getQna_re_lev() {
		return qna_re_lev;
	}
	public void setQna_re_lev(int qna_re_lev) {
		this.qna_re_lev = qna_re_lev;
	}
	public int getQna_re_seq() {
		return qna_re_seq;
	}
	public void setQna_re_seq(int qna_re_seq) {
		this.qna_re_seq = qna_re_seq;
	}
	public Date getQna_date() {
		return qna_date;
	}
	public void setQna_date(Date qna_date) {
		this.qna_date = qna_date;
	}
	@Override
	public String toString() {
		return "QnaDTO [qna_idx=" + qna_idx + ", qna_subject=" + qna_subject + ", qna_content=" + qna_content
				+ ", qna_file=" + qna_file + ", qna_real_file=" + qna_real_file + ", qna_re_ref=" + qna_re_ref
				+ ", qna_re_lev=" + qna_re_lev + ", qna_re_seq=" + qna_re_seq + ", qna_date=" + qna_date + "]";
	}
		

}
