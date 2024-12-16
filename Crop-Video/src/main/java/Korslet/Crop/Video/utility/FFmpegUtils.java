package Korslet.Crop.Video.utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

public class FFmpegUtils {

    public static String cropVideo(String inputFilePath, int width, int height, int x, int y, String outputDirectory) throws IOException, InterruptedException {
        String outputFileName = outputDirectory + FilenameUtils.getBaseName(inputFilePath) + "_cropped.mp4";
        new File(outputDirectory).mkdirs();

        String command = String.format(
                "ffmpeg -i %s -filter:v \"crop=%d:%d:%d:%d\" -c:a copy %s",
                inputFilePath, width, height, x, y, outputFileName
        );

        Process process = Runtime.getRuntime().exec(command);
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            throw new IOException("Error occurred while processing video");
        }

        return outputFileName;
    }
}