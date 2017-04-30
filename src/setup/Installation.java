package setup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Installation {
	// Setup configuration
	private String mInstallationLocation;

	// Setup progress
	private StringBuilder mInstallationStatus;
	private boolean mInstalledSuccessfully;

	public Installation() {
		mInstallationLocation = "C:\\ShopAssistance";

		mInstallationStatus = new StringBuilder();

		mInstalledSuccessfully = false;
	}

	public boolean isSuccessfull() {
		return mInstalledSuccessfully;
	}

	public String getIstallationStatus() {
		return mInstallationStatus.toString();
	}

	public String getIstallationSetting() {
		StringBuilder sb = new StringBuilder();
		sb.append("Installation localation:\n");
		sb.append(mInstallationLocation + "\n");
		sb.append('\n');
		sb.append("Database will be installed in installation folder.");
		return sb.toString();
	}

	public void install() {
		try {
			// Copy file to installation folder
			File installationFolder = new File(mInstallationLocation);

			// Make missing folders if not exists
			if (!installationFolder.exists()) {
				installationFolder.mkdirs();
				mInstallationStatus.append("Installtion folder created.\n");
			}

			// Copy program jar
			File jarFile = new File("Release/StoreManagement.jar");
			File installedJarFile = new File(mInstallationLocation + "\\StoreManagement.jar");

			copyFile(jarFile, installedJarFile);

			mInstallationStatus.append("Program files copied.\n");

			

			// Final success message
			mInstallationStatus.append("Program has been successfully installed!\n");
			mInstalledSuccessfully = true;
		} catch (Exception e) {
			mInstallationStatus.append("Installation failed!\n\n");
			// TODO: You could come up with better message for the failure
			mInstallationStatus.append(e);
		}
	}

	private static void copyFile(File source, File dest) throws IOException {
		Files.copy(source.toPath(), dest.toPath());
	}

	public void setInstallationLocation(String path) {
		mInstallationLocation = path;
	}

	public String getInstallationLocation() {
		return mInstallationLocation;
	}
}
