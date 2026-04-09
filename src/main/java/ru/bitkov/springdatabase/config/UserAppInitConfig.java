package ru.bitkov.springdatabase.config;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class UserAppInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Nullable
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {UserHibernateConfig.class};
    }

    @Nullable
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {UserWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
