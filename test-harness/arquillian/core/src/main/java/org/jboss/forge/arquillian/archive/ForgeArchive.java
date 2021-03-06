/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.forge.arquillian.archive;

import java.util.List;

import org.jboss.forge.furnace.addons.AddonDependency;
import org.jboss.forge.furnace.repositories.AddonDependencyEntry;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.asset.Asset;
import org.jboss.shrinkwrap.api.container.LibraryContainer;
import org.jboss.shrinkwrap.api.container.ResourceContainer;
import org.jboss.shrinkwrap.api.container.ServiceProviderContainer;

/**
 * Archive representing a Furnace AddonDependency deployment.
 * 
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 */
public interface ForgeArchive extends Archive<ForgeArchive>, LibraryContainer<ForgeArchive>,
         ResourceContainer<ForgeArchive>, ServiceProviderContainer<ForgeArchive>
{
   /**
    * Sets the current forge.xml descritor for this archive.
    */
   ForgeArchive setAsForgeXML(Asset resource) throws IllegalArgumentException;

   /**
    * Adds the given {@link AddonDependency} instances as addon module dependencies for this test deployment.
    */
   ForgeArchive addAsAddonDependencies(AddonDependencyEntry... dependencies);

   /**
    * Get the currently specified {@link AddonDependency} instances for this addon test deployment.
    */
   List<AddonDependencyEntry> getAddonDependencies();

   /**
    * Adds an empty beans.xml file in this archive
    */
   ForgeArchive addBeansXML();

   /**
    * Adds an beans.xml file in this archive with the specified content
    */
   ForgeArchive addBeansXML(Asset resource);

   /**
    * Add a basic service container, using the given service types as services in the deployment.
    * <p>
    * <b>WARNING: </b> Cannot be combined with other service containers.
    */
   ForgeArchive addAsLocalServices(Class<?>... serviceTypes);
}
