package org.dathan;

import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Charsets;
import com.google.common.base.Stopwatch;

public class PackedCirclePlotter {

    public static void main(String... args) throws IOException {
        final int miniCircleRadius = 10;
        final int outerCircleRadius = 400;
        final int width = 1400;
        final int height = 800;
        final int originX = 700;
        final int originY = 400;

        Stopwatch started = Stopwatch.createStarted();
        Circle origin = new Circle(new Point2D.Double(originX, originY), miniCircleRadius);
        CirclePacker circlePacker = new CirclePacker(origin, outerCircleRadius);

        List<Circle> packedCircles = circlePacker.getPackedCircles();
        long elapsed = started.stop().elapsed(TimeUnit.MILLISECONDS);
        System.out.println("Packed " + packedCircles.size() + " circles in " + elapsed + "ms");

        File outputFile = new File("output/packed.svg");
        outputFile.getParentFile().mkdirs();

        BufferedWriter writer = Files.newBufferedWriter(outputFile.toPath(), Charsets.UTF_8);

        writer.write(MessageFormat.format("<?xml version=\"1.0\" standalone=\"no\"?>\n" +
                "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \n" +
                "  \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\"><svg xmlns=\"http://www.w3.org/2000/svg\"\n" +
                "     version=\"1.1\" height=\"{0,number,#}\" width=\"{1,number,#}\">\n", height, width));

        writer.write(MessageFormat.format("    <circle cx=\"{0,number,#}\" cy=\"{1,number,#}\" r=\"{2,number,#}\" stroke=\"red\" stroke-width=\"5\" />\n",
                (int)origin.getCenter().getX(),
                (int)origin.getCenter().getY(),
                outerCircleRadius));

        for (Circle circle: packedCircles) {
            writer.write(MessageFormat.format("    <circle cx=\"{0,number,#}\" cy=\"{1,number,#}\" r=\"{2,number,#}\" fill=\"cyan\" stroke=\"cyan\" stroke-width=\"1\" />\n",
                    (int)circle.getCenter().getX(),
                    (int)circle.getCenter().getY(),
                    (int)circle.getRadius()));
        }

        writer.write("</svg>\n");
        writer.close();


    }
}
