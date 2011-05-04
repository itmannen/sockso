
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

        return 100;

    }

    /**
     *  Returns the number to offset results by
     *
     *  @return
     *
     */

    public int getOffset() {

        return 0;

    }

}
