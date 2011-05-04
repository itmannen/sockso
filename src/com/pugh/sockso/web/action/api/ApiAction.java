
package com.pugh.sockso.web.action.api;

import com.pugh.sockso.web.BadRequestException;
import com.pugh.sockso.web.action.WebAction;

import java.io.IOException;

import java.sql.SQLException;

abstract public class ApiAction extends WebAction {

    /**
     *  Returns the name of the api command to handle.
     *
     *  @return
     *
     */

    public abstract String getCommandName();

    /**
     *  Returns true if the action handled the API request, false otherwise
     *
     *  @return
     *
     */

    public abstract boolean handleApiRequest() throws BadRequestException, IOException, SQLException;

    /**
     *  Return the number of results to limit by
     *
     *  @return
     *
     */

    public int getLimit() {

        try {

            if ( getRequest().hasArgument( "limit" ) ) {
                int limit = Integer.parseInt( getRequest().getArgument( "limit" ) );
                return limit;
            }

        } catch (NumberFormatException ignored) {}

        return 100;

    }

    /**
     *  Returns the number to offset results by
     *
     *  @return
     *
     */

    public int getOffset() {

        try {

            if ( getRequest().hasArgument( "offset" ) ) {
                int offset = Integer.parseInt( getRequest().getArgument( "offset" ) );
                return offset;
            }

        } catch (NumberFormatException ignored) {}

        return 0;

    }

}
