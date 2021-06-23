package demo.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import demo.dao.EtudiantRepository;
import demo.entities.Etudiant;

@Controller
@RequestMapping(value = "/Etudiant")
public class EtudiantController {
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Value("${dir.images}")
	private String imagesDir;
	
	@RequestMapping(value = "/index")
	public String index(Model model,
			@RequestParam(name = "page",defaultValue = "0")int p,
			@RequestParam(name = "size",defaultValue = "5")int size,
			@RequestParam(name = "motCle",defaultValue = "")String mc) {
		Page<Etudiant> pageEtudiants=etudiantRepository.chercherEtudiants("%"+mc+"%", PageRequest.of(p, size));
		int pageCount=pageEtudiants.getTotalPages();
		int[] pages=new int[pageCount];
		for(int i=0; i<pageCount;i++) pages[i]=i;
		model.addAttribute("pages", pages);
		model.addAttribute("pageEtudiants", pageEtudiants);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		return "etudiants";
	}
	
	@RequestMapping(value = "/form" ,method=RequestMethod.GET)
	public String formEtudiant(Model model) {
		model.addAttribute("etudiant", new Etudiant());
		return "formEtudiant";
	}
	
	@RequestMapping(value = "/saveEtudiant" ,method=RequestMethod.POST)
	public String save(@Valid Etudiant etd, BindingResult bindingResult,
			@RequestParam(name = "picture")MultipartFile file) throws Exception {
		if(bindingResult.hasErrors()) {
			return "formEtudiant";
		}
		
		if(!(file.isEmpty())) {
			etd.setPhoto(file.getOriginalFilename());
		}
		etudiantRepository.save(etd);
		
		if(!(file.isEmpty())) {
			etd.setPhoto(file.getOriginalFilename());
			//file.transferTo(new File(System.getProperty("user.home")+"/sco/"+file.getOriginalFilename()));
			//file.transferTo(new File(imagesDir+file.getOriginalFilename()));
			file.transferTo(new File(imagesDir+etd.getId()));
		}
		//etudiantRepository.save(etd);
		return "redirect:index";
	}
	
	@RequestMapping(value = "/getPhoto",produces =MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long id) throws Exception {
		File f= new File(imagesDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value = "/supprimer")
	public String supprimer(Long id) {
		etudiantRepository.deleteById(id);
		return "redirect:index";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(Long id, Model model) {
		Etudiant etd=etudiantRepository.getById(id);
		model.addAttribute("etudiant", etd);
		return "EditEtudiant";
	}
	
	@RequestMapping(value = "/updateEtudiant" ,method=RequestMethod.POST)
	public String update(@Valid Etudiant etd, BindingResult bindingResult,
			@RequestParam(name = "picture")MultipartFile file)  {
		if(bindingResult.hasErrors()) {
			return "EditEtudiant";
		}
		
		if(!(file.isEmpty())) {
			etd.setPhoto(file.getOriginalFilename());
		}
		etudiantRepository.save(etd);
		
		if(!(file.isEmpty())) {
			etd.setPhoto(file.getOriginalFilename());
			//file.transferTo(new File(System.getProperty("user.home")+"/sco/"+file.getOriginalFilename()));
			//file.transferTo(new File(imagesDir+file.getOriginalFilename()));
			try {
				file.transferTo(new File(imagesDir+etd.getId()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "redirect:index";
	}

}
