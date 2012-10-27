/*
HTTP stub server written in Java with embedded Jetty

Copyright (C) 2012 Alexander Zagniotov, Isa Goksu and Eric Mrak

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.stubby.handlers.strategy;

import org.eclipse.jetty.http.HttpStatus;
import org.stubby.handlers.HttpRequestInfo;
import org.stubby.utils.HandlerUtils;
import org.stubby.utils.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class NotFoundResponseHandlingStrategy implements StubResponseHandlingStrategy {

   public NotFoundResponseHandlingStrategy() {

   }

   @Override
   public void handle(final HttpServletResponse response, final HttpRequestInfo httpRequestInfo) throws IOException {
      HandlerUtils.setResponseMainHeaders(response);
      final String postMessage = (StringUtils.isSet(httpRequestInfo.getPostBody()) ? " for post data: " + httpRequestInfo.getPostBody() : "");
      final String error = String.format("No data found for %s request at URI %s", httpRequestInfo.getMethod(), httpRequestInfo.getUrl() + postMessage);
      HandlerUtils.configureErrorResponse(response, HttpStatus.NOT_FOUND_404, error);
   }
}