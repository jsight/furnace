/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.furnace.manager.maven.addon;

import java.io.File;
import java.util.Set;

import org.jboss.forge.furnace.addons.AddonId;
import org.jboss.forge.furnace.manager.spi.AddonDependencyResolver;
import org.jboss.forge.furnace.manager.spi.AddonInfo;
import org.jboss.forge.furnace.manager.spi.Response;
import org.jboss.forge.furnace.repositories.AddonDependencyEntry;

/**
 * Makes {@link AddonInfo#getResources()} lazy
 * 
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 * 
 */
class LazyAddonInfo implements AddonInfo
{
   private final AddonDependencyResolver resolver;
   private AddonInfoBuilder builder;

   public LazyAddonInfo(AddonDependencyResolver resolver, AddonInfoBuilder builder)
   {
      this.resolver = resolver;
      this.builder = builder;
   }

   @Override
   public Set<File> getResources()
   {
      resolveResources(builder);
      return builder.getResources();
   }

   @Override
   public Set<AddonInfo> getRequiredAddons()
   {
      return builder.getRequiredAddons();
   }

   @Override
   public Set<AddonInfo> getOptionalAddons()
   {
      return builder.getOptionalAddons();
   }

   @Override
   public AddonId getAddon()
   {
      return builder.getAddon();
   }

   @Override
   public Set<AddonDependencyEntry> getDependencyEntries()
   {
      return builder.getDependencyEntries();
   }

   @Override
   public boolean equals(Object obj)
   {
      return builder.equals(obj);
   }

   @Override
   public int hashCode()
   {
      return builder.hashCode();
   }

   @Override
   public String toString()
   {
      return builder.toString();
   }

   public void resolveResources(AddonInfoBuilder addonInfo)
   {
      AddonId addon = addonInfo.getAddon();
      Response<File[]> resources = resolver.resolveResources(addon);
      for (File resource : resources.get())
      {
         addonInfo.addResource(resource);
      }
   }
}