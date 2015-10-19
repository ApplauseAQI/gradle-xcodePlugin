/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openbakery.signing

import org.gradle.api.tasks.TaskAction
import org.openbakery.AbstractXcodeTask

class ProvisioningInstallTask extends AbstractXcodeTask {


	ProvisioningInstallTask() {
		super()
		this.description = "Installs the given provisioning profile"

		this.setOnlyIf {
			return (project.xcodebuild.sdk == null || !project.xcodebuild.sdk.startsWith("iphonesimulator"))
		}
	}

	@TaskAction
	def install() {

		if (project.xcodebuild.sdk != null && project.xcodebuild.sdk.startsWith("iphonesimulator")) {
			logger.lifecycle("The simulator build does not need a provisioning profile")
			return
		}

		if (project.xcodebuild.signing.mobileProvisionURI == null) {
			logger.lifecycle("No provisioning profile specifed so do nothing here")
			return
		}

		def mobileProvisionFile = download(project.xcodebuild.signing.mobileProvisionDestinationRoot, project.xcodebuild.signing.mobileProvisionURI)
		project.xcodebuild.signing.mobileProvisionFile = new File(mobileProvisionFile)

		File provisionPath = new File(System.getProperty("user.home") + "/Library/MobileDevice/Provisioning Profiles/");
		if (!provisionPath.exists()) {
			provisionPath.mkdirs()
		}



		commandRunner.run(["/bin/ln", "-s", mobileProvisionFile, project.xcodebuild.signing.mobileProvisionFileLinkToLibrary.absolutePath])
	}
}
