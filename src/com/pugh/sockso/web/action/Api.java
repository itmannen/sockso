
package com.pugh.sockso.web.action;

import com.pugh.sockso.ObjectCache;
import com.pugh.sockso.music.CollectionManager;
import com.pugh.sockso.web.BadRequestException;
import com.pugh.sockso.web.action.api.ApiAction;
import com.pugh.sockso.web.action.api.PlaylistsAction;
import com.pugh.sockso.web.action.api.RootAction;

import java.util.Hashtable;

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

    private static final Hashtable<String, ApiAction> apiActions;

    static {

        apiActions = new Hashtable<String, ApiAction>();
        initApiActions();

    }

    public Api( final CollectionManager cm, final ObjectCache cache ) {

        this.cm = cm;
        this.cache = cache;

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

        int paramCount = getRequest().getPlayParams(false).length;
        initApiActions();

        String command = (paramCount == 0) ? "" : getRequest().getPlayParams(false)[0];

        ApiAction action = apiActions.get(command);
        if ( action != null ) {

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
    protected static void initApiActions() {

        ApiAction[] actions = { new RootAction(),
                                new PlaylistsAction() };

        for (ApiAction action: actions) {
            apiActions.put(action.getCommandName(), action);
        }

    }

}
