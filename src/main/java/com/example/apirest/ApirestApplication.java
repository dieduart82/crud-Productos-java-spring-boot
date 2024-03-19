package com.example.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApirestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApirestApplication.class, args);
    }


}






//@RestController
//class CursoController {

//	@Autowired
//	private CursoRepository cursoRepository;
//	@GetMapping("/cursos")
//	List<Curso> index(){
//		return cursoRepository.findAll();
//	}
//}
