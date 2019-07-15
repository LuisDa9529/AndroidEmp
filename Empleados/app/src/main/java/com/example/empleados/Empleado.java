package com.example.empleados;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Empleado implements Serializable{
	private int emp_no;
	private String birth_date;
	private String first_name;
	private String last_name;
	private String gender;
	private String hire_date;

	public String toString() {
		return String.format(emp_no + " , " + birth_date + " , " + first_name + " , " + last_name + " , " + gender + " , " + hire_date);
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public Date getBirth_date() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date date = null;

		try {
			date = formatter.parse(birth_date);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}
	public Boolean setBirth_date(String dateInString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		Boolean res = true;
		String newFormat = "yyyy-MM-dd'T'HH:mm:ss";


		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			try {
				date = formatter2.parse(dateInString);
			} catch (ParseException e1) {
				res = false;
			}
		}

		String newDate = formatter.format(date);

		this.birth_date = newDate;
		return res;

	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getHire_date() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date date = null;

		try {
			date = formatter.parse(birth_date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public Boolean setHire_date(String dateInString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		Boolean res = true;
		String newFormat = "yyyy-MM-dd'T'HH:mm:ss";


		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			try {
				date = formatter2.parse(dateInString);
			} catch (ParseException e1) {
				res = false;
			}
		}

		String newDate = formatter.format(date);

		this.hire_date = newDate;
		return res;
	}

}


