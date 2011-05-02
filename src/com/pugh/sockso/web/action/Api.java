/*
 * Api.java
 * 
 * End point for Sockso Web API methods.
 * 
 */
package com.pugh.sockso.web.action;

import java.io.IOException;

import org.apache.log4j.Logger;
import com.pugh.sockso.ObjectCache;
import com.pugh.sockso.music.CollectionManager;
import com.pugh.sockso.templates.api.TApiServerInfo;
import com.pugh.sockso.templates.json.TServerInfo;
import com.pugh.sockso.web.Request;

public class Api extends WebAction {
	

    private static final Logger log = Logger.getLogger( Jsoner.class );
    
    private final CollectionManager cm;

    private final ObjectCache cache;
    
    public Api( final CollectionManager cm, final ObjectCache cache ) {
        
        this.cm = cm;
        this.cache = cache;
        
    }

	@Override
	public void handleRequest() throws Exception {
		
		final Request req = getRequest();
		
		if ( req.getParamCount() == 1 ) {
			
			serverInfo();
			return;
			
		}
	}

	protected void serverInfo() throws IOException {
	
        final TApiServerInfo tpl = new TApiServerInfo();
        tpl.setProperties( getProperties() );

        getResponse().showJson( tpl.makeRenderer() );
		
	}
}
