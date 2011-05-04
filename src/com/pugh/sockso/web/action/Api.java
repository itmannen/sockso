
package com.pugh.sockso.web.action;

import com.pugh.sockso.ObjectCache;
import com.pugh.sockso.music.CollectionManager;
import com.pugh.sockso.web.BadRequestException;
import com.pugh.sockso.web.action.api.ApiAction;
import com.pugh.sockso.web.action.api.RootAction;

import java.util.Vector;

import org.apache.log4j.Logger;

import java.io.IOException;

import java.sql.SQLException;

/**
 *  End point for Sockso Web API methods.
 * 
 */
public class Api extends WebAction {

    private static final Logger log = Logger.getLogger( Api.class );

    private final CollectionManager cm;

    private final ObjectCache cache;

    private final Vector<ApiAction> apiActions;

    public Api( final CollectionManager cm, final ObjectCache cache ) {

        this.cm = cm;
        this.cache = cache;

        apiActions = new Vector<ApiAction>();

    }

    /**
     *  Run through API actions looking for one to handle the request
     *
     *  @throws BadRequestException
     *  @throws IOException
     *  @throws SQLException
     *
     */

    @Override
    public void handleRequest() throws BadRequestException, IOException, SQLException {

        initApiActions();

        for ( ApiAction action : apiActions ) {

            action.init( getRequest(), getResponse(), getUser(), getLocale() );
            action.setDatabase( getDatabase() );
            action.setProperties( getProperties() );

            if ( action.handleApiRequest() ) {
                return;
            }

        }

        throw new BadRequestException( "Unknown API Action" );

    }

    /**
     *  Init apiActions with all the actions we can process
     *
     */
    protected void initApiActions() {

        apiActions.add( new RootAction() );

    }

}
