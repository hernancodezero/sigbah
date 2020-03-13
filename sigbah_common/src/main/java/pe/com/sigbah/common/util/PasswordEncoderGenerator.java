package pe.com.sigbah.common.util;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @className: PasswordEncoderGenerator.java
 * @description: 
 * @date: 9 de may. de 2017
 * @author: Junior Huaman Flores.
 */
public class PasswordEncoderGenerator implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private static Log LOGGER = LogFactory.getLog(PasswordEncoderGenerator.class.getName());

	/**
	 * @param password
	 * @return contraseña encriptada
	 */
	public static String obtenerPasswordEncriptado(String password) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e)  {
            LOGGER.debug(e.getMessage().toString());
        }
		return generatedPassword;
	}

}
