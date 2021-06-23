package demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import demo.dao.EtudiantRepository;
import demo.entities.Etudiant;

@SpringBootApplication
public class FormulaireEtudiantApplication implements CommandLineRunner {
	@Autowired
	private EtudiantRepository etudiantRepository;

	public static void main(String[] args) {
		SpringApplication.run(FormulaireEtudiantApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*
		DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
		etudiantRepository.save(new Etudiant("Bechir", df.parse("1990-08-22"), "bechir@gmail.com", "bechir.jpg"));
		etudiantRepository.save(new Etudiant("camille", df.parse("1994-11-30"), "camille@gmail.com", "camille.jpg"));
		etudiantRepository.save(new Etudiant("Imen", df.parse("1982-07-12"), "imen@gmail.com", "imen.jpg"));
		etudiantRepository.save(new Etudiant("anis", df.parse("1982-10-12"), "anis@gmail.com", "anis.jpg"));
		
		etudiantRepository.save(new Etudiant("imed", df.parse("1992-08-22"), "imed@gmail.com", "imed.jpg"));
		etudiantRepository.save(new Etudiant("issalene", df.parse("1995-12-31"), "issalene@gmail.com", "issalene.jpg"));
		etudiantRepository.save(new Etudiant("Imen", df.parse("1982-07-12"), "imen@gmail.com", "imen.jpg"));
		etudiantRepository.save(new Etudiant("Kamel", df.parse("1982-09-12"), "kamel@gmail.com", "kamel.jpg"));
		
		//Page<Etudiant> etds=etudiantRepository.findAll(PageRequest.of(0, 5));
		Page<Etudiant> etds=etudiantRepository.chercherEtudiants("%A%",PageRequest.of(0, 4));
		etds.forEach(e->System.out.println(e.getNom()));
		*/
		
	}

}
