/**
 * 
 */
package mblog.web.controller.desk.group;

import javax.servlet.http.HttpServletRequest;

import mblog.planet.PostPlanet;
import mblog.web.controller.BaseController;
import mblog.web.controller.desk.Views;
import mtons.modules.pojos.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 相片走廊
 * @author langhsu
 *
 */
@Controller
public class GalleryController extends BaseController {
	@Autowired
	private PostPlanet postPlanet;
	
	private int jsonMaxResults = 8;
	
	@RequestMapping("/gallery")
	public String view(Integer pn, ModelMap model, HttpServletRequest request) {
		int groupId = ServletRequestUtils.getIntParameter(request, "g", 0);
		Paging page = wrapPage(pn);
		page.setMaxResults(jsonMaxResults);

		page = postPlanet.gallery(page, groupId, "newest");
		model.put("page", page);
		model.put("groupId", groupId);
		return getView(Views.BROWSE_GALLERY);
	}
	
	@RequestMapping("/gallery_snippet/{pn}")
	public String snippet(@PathVariable Integer pn, ModelMap model, HttpServletRequest request) {
		int groupId = ServletRequestUtils.getIntParameter(request, "g", 0);
		Paging page = wrapPage(pn);
		page.setMaxResults(jsonMaxResults);
		
		page = postPlanet.gallery(page, groupId, "newest");
		model.put("page", page);
		model.put("groupId", groupId);
		return getView(Views.BROWSE_GALLERY_SNIPPET);
	}
}