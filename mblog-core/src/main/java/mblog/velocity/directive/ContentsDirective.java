/**
 * 
 */
package mblog.velocity.directive;

import java.io.IOException;

import javax.servlet.ServletRequest;

import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.web.bind.ServletRequestUtils;

import mblog.base.context.SpringContextHolder;
import mblog.base.lang.Consts;
import mblog.core.biz.PostBiz;
import mblog.velocity.BaseDirective;
import mblog.velocity.handler.RenderHandler;
import mtons.modules.pojos.Paging;

/**
 * 内容查询
 * @author langhsu
 *
 */
public class ContentsDirective extends BaseDirective {
	private PostBiz postPlanet;

	@Override
	public void initBean() {
		postPlanet = SpringContextHolder.getBean(PostBiz.class);
	}

	@Override
	public String getName() {
		return "contents";
	}

	@Override
	public int getType() {
		return BLOCK;
	}

	@Override
	public boolean render(RenderHandler handler) throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, IOException {
		ServletRequest request = handler.getRequest();
		
        String ord = ServletRequestUtils.getStringParameter(request, "ord", Consts.order.NEWEST);
        int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
        
        int groupId = handler.getIntParameter(0);
        String alias = handler.getStringParameter(1);
        
        Paging paging = new Paging(pn, 12);
		Paging result = postPlanet.paging(paging, groupId, ord);
		
		handler.put(alias, result);
		handler.doRender();
		
		postRender(handler.getContext());
		return true;
	}

}
