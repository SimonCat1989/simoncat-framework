package com.simoncat.framework.lucene.config;

import java.io.File;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LuceneSettingFactory implements ObjectFactory {

	/**
     * ENC(Environment Naming Context).
     */
    private static final String JNDI_ENC = "java:comp/env/";

    /**
     * JNDI name for configure.
     */
    private static final String RES_NAME = JNDI_ENC + "simoncat/LuceneSetting";
	
	private static final String PROPERTY_NAME_OF_PATH = "path";

	@Override
	public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
		if ((obj == null) || !(obj instanceof Reference)) {
            return null;
        }

        Reference ref = (Reference)obj;
        if (!"java.io.File".equals(ref.getClassName())) {
            log.warn("Supports only java.io.File!");
            return null;
        }

        RefAddr credentialsFilePath = ref.get(PROPERTY_NAME_OF_PATH);
        if (credentialsFilePath == null) {
        	log.warn("Property [{}] was not found.", PROPERTY_NAME_OF_PATH);
            return null;
        }

        return new File(credentialsFilePath.getContent().toString());
	}
	
	/**
     * @return Notification prop file
     */
    static File getLuceneSettingProperties() {
        InitialContext ictx = null;
        try {
            ictx = new InitialContext();
            Object result = ictx.lookup(RES_NAME);
            return (File)result;
        } catch (NamingException e) {
            log.warn(System.lineSeparator()
                    + "--------------------------------------------------"
                    + System.lineSeparator()
                    + e.getLocalizedMessage()
                    + System.lineSeparator()
                    + "If this process runs on Tomcat Server, Please check settings."
                    + System.lineSeparator()
                    + "--------------------------------------------------"
                    + "");
            return null;
        } finally {
            if (ictx != null) {
                try {
                    ictx.close();
                } catch (NamingException ex) {
                	log.warn("Can NOT close Initial Context correctly!");
                }
            }
        }
    }
}
