package com.studentManagement.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.studentManagement.entity.Student;
import com.studentManagement.entity.StudentIdGenerator;
import com.studentManagement.services.StudentServices;

@Controller
public class StudentController {
    StudentServices ss;
    StudentIdGenerator sI=new StudentIdGenerator();
    public StudentController(StudentServices ss) {
        this.ss = ss;
    }

    @GetMapping("/")
    public ModelAndView getIndex() {
        return new ModelAndView("index");
    }

    @GetMapping("/addStudentPage")
    public ModelAndView addStudentPage() {
        return new ModelAndView("addStudent");
    }

    @GetMapping("/getStudent")
    public ModelAndView getStudent(@RequestParam("stuId") String stuId) {
        ModelAndView modelAndView = new ModelAndView("viewStudent");
        Student st = ss.getStudent(stuId);
        modelAndView.addObject("student", st);
        return modelAndView;
    }

    @GetMapping("/getStudentPage")
    public ModelAndView getStudentPage() {
        return new ModelAndView("getStudent");
    }

    @PostMapping("/addStudent")
    public String addStudent(@RequestParam("stuName") String stuName,
            @RequestParam("stuGender") String stuGender, @RequestParam("stuDob") String stuDob,
            @RequestParam("stuAddress") String stuAddress, @RequestParam("stufatherName") String stufathername,
            @RequestParam("stuMotherName") String stuMotherName, @RequestParam("stuBg") String stuBg,
            @RequestParam("stuPn") String stuPn,
            @RequestParam("stuAdharNo")String stuAdharNo,
            @RequestParam("image") MultipartFile image) throws IOException {
    	
    	String imageName = saveImage(image,stuName,stuAdharNo);

        Student st = new Student(stuName, stuGender, stuDob, stuAddress, stufathername, stuMotherName, stuBg, stuPn,imageName,stuAdharNo);
        ss.addStudent(st);
        return "index";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@RequestParam("stuId") String stuId, @RequestParam("stuName") String stuName,
            @RequestParam("stuGender") String stuGender, @RequestParam("stuDob") String stuDob,
            @RequestParam("stuAddress") String stuAddress, @RequestParam("stufatherName") String stufathername,
            @RequestParam("stuMotherName") String stuMotherName, @RequestParam("stuBg") String stuBg,
            @RequestParam("stuPn") String stuPn,
            @RequestParam("stuAdharNo")String stuAdharNo,
            @RequestParam(value = "newImage", required = false) MultipartFile newImage) throws IOException {

        String imageName = saveImage(newImage, stuName, stuAdharNo);
        Student st = ss.getStudent(stuId);
        st.setStuName(stuName);
        st.setStuGender(stuGender);
        st.setStuDob(stuDob);
        st.setStuAddress(stuAddress);
        st.setStufatherName(stufathername);
        st.setStuMotherName(stuMotherName);
        st.setStuBg(stuBg);
        st.setStuPn(stuPn);
        st.setImage(imageName);
        st.setStuAdharNo(stuAdharNo);
        ss.upDateStudent(st);
        return "index";
    }

    @GetMapping("/updatePage")
    public ModelAndView getStudentToUpdate(@RequestParam("stuId") String stuId) {
        ModelAndView modelAndView = new ModelAndView("update");
        Student st = ss.getStudent(stuId);
        modelAndView.addObject("student", st);
        return modelAndView;
    }

    @GetMapping("/getAllStudents")
    public ModelAndView getAllStudents() {
        List<Student> studentList = ss.getAllStudent();
        ModelAndView modelAndView = new ModelAndView("/getAllStudents");
        modelAndView.addObject("studentList", studentList);
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(@RequestParam("stuId") String stuId) throws IOException {
        // Retrieve the student to get the image file name
        Student student = ss.getStudent(stuId);
        String imageName = student.getImage();

        // Delete the associated image file
        if (StringUtils.hasText(imageName)) {
            deleteImage(imageName);
        }

        ss.deleteStudent(stuId);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("message", "Student deleted successfully" + stuId);
        return modelAndView;
    }

    // Save the image with 

    
    private String saveImage(MultipartFile image, String stuName, String stuAdharNo) throws IOException {
        if (image == null || image.isEmpty()) {
            return null;
        }

        String originalFilename = image.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = stuName + "_" + stuAdharNo + fileExtension;

        String uploadDirectory = "C:/Users/Vini/Documents/workspace-spring-tool-suite-4-4.17.2.RELEASE.Project/StudentManagement/src/main/resources/static/stuProfileImages";

        Path uploadPath = Path.of(uploadDirectory);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            // Handle the exception appropriately
            throw new IOException("Failed to save the image file", e);
        }
    }

    		  
//	private String generateUniqueFileName(String originalFileName) {
//		String fileExtension = StringUtils.getFilenameExtension(originalFileName);
//		String uniqueFileName = UUID.randomUUID().toString();
//
//		if (StringUtils.hasText(fileExtension)) {
//			return uniqueFileName + "." + fileExtension;
//		} else {
//			return uniqueFileName;
//		}
//	}
    
    

    // Delete the image file
    private void deleteImage(String imageName) throws IOException {
    	  String uploadDirectory = "C:\\Users\\Vini\\Documents\\workspace-spring-tool-suite-4-4.17.2.RELEASE.Project\\StudentManagement\\src\\main\\resources\\static\\stuProfileImages";      
    	  Path imagePath = Path.of(uploadDirectory, imageName);
        Files.deleteIfExists(imagePath);
    }
}


