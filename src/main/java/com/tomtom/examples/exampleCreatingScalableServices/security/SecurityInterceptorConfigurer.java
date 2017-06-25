/*
 * Copyright (C) 2016-2017, Stichting Mapcode Foundation (http://www.mapcode.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Copyright (C) 2017, TomTom International BV (http://www.tomtom.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tomtom.examples.exampleCreatingScalableServices.security;

import com.google.inject.Inject;
import com.tomtom.examples.exampleCreatingScalableServices.FutureBasedResource;
import com.tomtom.examples.exampleCreatingScalableServices.SimpleThreadBasedResource;
import com.tomtom.speedtools.rest.security.SecurityInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

/**
 * This class configures the {@link SecurityInterceptor} to require ro disabled security for REST
 * methods. The class should be treated as an eager singleton, which is instantiated before the
 * application really starts. Due to its dependency on the {@link SecurityInterceptor}, that class
 * will be instantiated just before this one.
 */
public class SecurityInterceptorConfigurer {
    @Nonnull
    private static final Logger LOG = LoggerFactory.getLogger(SecurityInterceptorConfigurer.class);

    @Inject
    public SecurityInterceptorConfigurer(@Nonnull final SecurityInterceptor securityInterceptor) {
        LOG.info("SecurityInterceptorConfigurer: configure security");

        /**
         * Make all methods available without authentication. Default is that authentication is required.
         */
        securityInterceptor.disableAuthenticationForClass(FutureBasedResource.class);
        securityInterceptor.disableAuthenticationForClass(SimpleThreadBasedResource.class);

        /**
         * To set up authentication for a specific mehtod:
         * securityInterceptor.enableAuthenticationForMethod(FutureBasedResource.class, "getVersion");
         */
    }
}
