
package com.pugh.sockso.web.action.api;

import com.pugh.sockso.music.Playlist;
import com.pugh.sockso.templates.api.TApiPlaylists;
import com.pugh.sockso.web.BadRequestException;
import com.pugh.sockso.web.Request;

import java.io.IOException;

import java.sql.SQLException;

public class PlaylistsAction extends ApiAction {

    public boolean handleApiRequest() throws SQLException, IOException, BadRequestException {

        final Request req = getRequest();

        if ( req.getParamCount() == 2 ) {
            handleRequest();
            return true;
        }

        return false;

    }

    public void handleRequest() throws SQLException, IOException, BadRequestException {
        
        final TApiPlaylists tpl = new TApiPlaylists();
        tpl.setPlaylists( Playlist.getPlaylists(getDatabase(), getUser(), getLimit(), getOffset(), true));

        getResponse().showJson( tpl.makeRenderer() );
        
    }

    @Override
    public String getCommandName() {

        return "playlists";

    }


}
