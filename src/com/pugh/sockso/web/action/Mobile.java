
package com.pugh.sockso.web.action;

import com.pugh.sockso.templates.web.mobile.TIndex;

import java.io.IOException;

/**
 *  Mobile version of the web interface
 *
 */
 
public class Mobile extends WebAction {

    /**
     *  Show the mobile version of the site (this is all driven
     *  by javascript after this initial load
     *
     *  @throws IOException
     
     */
     
    public void handleRequest() throws IOException {
    
        final TIndex tpl = new TIndex();
        tpl.setProperties( getProperties() );
        tpl.setLocale( getLocale() );
        
        getResponse().showHtml( tpl.makeRenderer() );
    
    }

}
