package org.sboj.config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.faces.annotation.FacesConfig;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class ApplicationBeanConfiguration {

    @Produces
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
