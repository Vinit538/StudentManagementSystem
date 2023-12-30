package com.studentManagement.entity;

import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(generator = "student_id_generator")
    @GenericGenerator(name = "student_id_generator", strategy = "com.studentManagement.entity.StudentIdGenerator")
    private String stuId;
    private String stuName;
    private int slno;
    private String stuGender;
    private String stuDob;
    private String stuAddress;
    private String stufatherName;
    private String stuMotherName;
    private String stuBg;
    private String stuPn;
    private String stuAdharNo;
    
    @Lob
    private String image;

    public Student() {
        super();
    }
    StudentIdGenerator si=new StudentIdGenerator();
    
    public Student(String stuName, String stuGender, String stuDob, String stuAddress, String stufatherName,
            String stuMotherName, String stuBg, String stuPn, String image,String stuAdharNo) {
        super();
        this.stuName = stuName;
        //this.stuId = generateStudentId();
        this.stuGender = stuGender;
        this.stuDob = stuDob;
        this.stuAddress = stuAddress;
        this.stufatherName = stufatherName;
        this.stuMotherName = stuMotherName;
        this.stuBg = stuBg;
        this.stuPn = stuPn;
        this.image = image;
        this.stuAdharNo=stuAdharNo;
        this.slno=getslno();
    }
    
    
    public int getslno()
    {
    	int lastslno=si.fetchLastCounterValue();
    	System.out.println("getslno "+stuId);
    	if(lastslno != 0)
    	{
    		 return lastslno+1;
    	}
    	else
    		{
    			return 1;
    		}
    
    }    

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getSlno() {
        return slno;
    }

    public void setSlno(int slno) {
        this.slno = slno;
    }

    public String getStuGender() {
        return stuGender;
    }

    public void setStuGender(String stuGender) {
        this.stuGender = stuGender;
    }

    public String getStuDob() {
        return stuDob;
    }

    public void setStuDob(String stuDob) {
        this.stuDob = stuDob;
    }

    public String getStuAddress() {
        return stuAddress;
    }

    public void setStuAddress(String stuAddress) {
        this.stuAddress = stuAddress;
    }

    public String getStufatherName() {
        return stufatherName;
    }

    public void setStufatherName(String stufatherName) {
        this.stufatherName = stufatherName;
    }

    public String getStuMotherName() {
        return stuMotherName;
    }

    public void setStuMotherName(String stuMotherName) {
        this.stuMotherName = stuMotherName;
    }

    public String getStuBg() {
        return stuBg;
    }

    public void setStuBg(String stuBg) {
        this.stuBg = stuBg;
    }

    public String getStuPn() {
        return stuPn;
    }

    public void setStuPn(String stuPn) {
        this.stuPn = stuPn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


	public String getStuAdharNo() {
		return stuAdharNo;
	}


	public void setStuAdharNo(String stuAdharNo) {
		this.stuAdharNo = stuAdharNo;
	}

    
}
