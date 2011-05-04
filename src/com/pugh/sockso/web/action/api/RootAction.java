
package com.pugh.sockso.web.action.api;

import com.pugh.sockso.templates.api.TApiServerInfo;

import java.io.IOException;

public class RootAction extends ApiAction {

    public boolean handleApiRequest() throws IOException {

        if ( getRequest().getParamCount() == 1 ) {
            handleRequest();
            return true;
        }

        return false;

    }

    public void handleRequest() throws IOException {

        final TApiServerInfo tpl = new TApiServerInfo();
        tpl.setProperties( getProperties() );

        getResponse().showJson( tpl.makeRenderer() );
        
    }

    @Override
    public String getCommandName() {
        return "";
    }

}
