package at.spengergasse.sj2324posproject.presentation.www;

public interface RedirectForwardSupport {
    default String redirect(String url){
        return "redirect:"+url;
    }
}
