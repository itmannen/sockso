/*
 * Api.java
 * 
 * End point for Sockso Web API methods.
 * 
 */
package com.pugh.sockso.web.action;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import com.pugh.sockso.ObjectCache;
import com.pugh.sockso.music.CollectionManager;
import com.pugh.sockso.music.Playlist;
import com.pugh.sockso.templates.api.TApiPlaylists;
import com.pugh.sockso.templates.api.TApiServerInfo;
import com.pugh.sockso.web.BadRequestException;
import com.pugh.sockso.web.Request;

public class Api extends WebAction {

    private static final Logger log = Logger.getLogger( Jsoner.class );

    private final CollectionManager cm;

    private final ObjectCache cache;

    private int offset = 0;
    private int limit = 100;

    public Api( final CollectionManager cm, final ObjectCache cache ) {

        this.cm = cm;
        this.cache = cache;

    }

    @Override
    public void handleRequest() throws BadRequestException, IOException, SQLException {

        final Request req = getRequest();

        processRequestArguments();

        if ( req.getParamCount() == 1 ) {

            serverInfo();
            return;

        }

        final String type = req.getUrlParam( 1 );

        if ( type.equals( "playlists" ) )
            playlists();

    }

    protected void serverInfo() throws IOException {

        final TApiServerInfo tpl = new TApiServerInfo();
        tpl.setProperties( getProperties() );

        getResponse().showJson( tpl.makeRenderer() );

    }

    protected void playlists() throws SQLException, IOException, BadRequestException {

        final Request req = getRequest();

        if ( req.getParamCount() == 2 ) {
            listPlaylists();
            return;
        }

    }

    protected void listPlaylists() throws SQLException, IOException, BadRequestException {
        final TApiPlaylists tpl = new TApiPlaylists();
        tpl.setPlaylists( Playlist.getPlaylists(getDatabase(), getUser(), limit, offset, true));

        getResponse().showJson( tpl.makeRenderer() );
    }

    protected void processRequestArguments() {

        final Request req = getRequest();

        try {

            if ( req.hasArgument( "offset" ) ) {
                offset = Integer.parseInt( req.getArgument( "offset" ) );
            }

            if ( req.hasArgument( "limit" ) ) {
                limit = Integer.parseInt( req.getArgument( "limit" ) );
            }

        } catch (NumberFormatException e) { }

    }

}
