package demo.sec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class SecurityController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}
	
	@GetMapping("/")
	public String def() {
		return "redirect:/Etudiant/index";
	}

}
