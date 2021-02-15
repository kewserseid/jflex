/*
 * Copyright (C) 2018-2019 Google, LLC.
 *
 * License: https://opensource.org/licenses/BSD-3-Clause
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided with
 * the distribution.
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.jflex.ucd_generator.emitter.unicode_properties;

import com.google.common.base.Joiner;
import de.jflex.ucd_generator.emitter.common.UcdEmitter;
import de.jflex.ucd_generator.ucd.UcdVersions;
import de.jflex.util.javac.JavaPackageUtils;
import de.jflex.velocity.Velocity;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.velocity.runtime.parser.ParseException;

/** UnicodePropertiesEmitter for {@code UnicodeProperties.java}. */
public class UnicodePropertiesEmitter extends UcdEmitter {

  private static final String TEMPLATE_NAME = "UnicodeProperties.java";
  private static final String UNICODE_PROPERTIES_TEMPLATE =
      JavaPackageUtils.getPathForClass(UnicodePropertiesEmitter.class)
          + "/"
          + TEMPLATE_NAME
          + ".vm";

  private final UcdVersions versions;

  public UnicodePropertiesEmitter(String targetPackage, UcdVersions versions) {
    super(targetPackage);
    this.versions = versions;
  }

  public void emitUnicodeProperties(OutputStream output) throws IOException, ParseException {
    UnicodePropertiesVars unicodePropertiesVars = createUnicodePropertiesVars();
    Velocity.render(
        readResource(UNICODE_PROPERTIES_TEMPLATE), TEMPLATE_NAME, unicodePropertiesVars, output);
  }

  private UnicodePropertiesVars createUnicodePropertiesVars() {
    UnicodePropertiesVars unicodePropertiesVars = new UnicodePropertiesVars();
    unicodePropertiesVars.templateName = TEMPLATE_NAME;
    unicodePropertiesVars.packageName = getTargetPackage();
    unicodePropertiesVars.versionsAsString = Joiner.on(", ").join(versions.expandAllVersions());
    unicodePropertiesVars.latestVersion = versions.getLastVersion().toMajorMinorString();
    unicodePropertiesVars.versions = versions.versionsAsList();
    unicodePropertiesVars.ucdVersions = versions;
    return unicodePropertiesVars;
  }
}