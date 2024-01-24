package org.vrspace.example.server;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.vrspace.server.core.ClientFactory;
import org.vrspace.server.core.VRObjectRepository;
import org.vrspace.server.obj.Client;
import org.vrspace.server.obj.User;

/**
 * An example of custom user authentication, simply looks up for MY_USERNAME and
 * MY_PASSWORD HTTP headers. This is triggered when client establishes websocket
 * connection. This implementation is much alike DefaultClientFactory, except it
 * doesn't implement createGuestClient. Thus if local-user-name attribute does
 * not exist in the session, the server throws SecurityException.
 */
public class MyClientFactory implements ClientFactory {

  /**
   * Note that ClientFactory has more methods, but this one is the only one
   * mandatory. It's copied from the DefaultClientFactory actually. Simply looks
   * up the database by local-user-name session attribute, and either returns the
   * client, or throws the exception.
   */
  @Override
  public <T extends Client> T findClient(Class<T> clientClass, Principal principal, VRObjectRepository db,
      HttpHeaders headers, Map<String, Object> attributes) {
    // Object name = attributes.get(clientAttribute());
    String name = principal.getName();
    if (name != null) {
      T user = db.getClientByName((String) name, clientClass);
      if (user == null) {
        user = (T) new User(name);
        db.save(user);
      }
      return user;
    }
    throw new SecurityException("Unknown client name: " + name);
  }
}
