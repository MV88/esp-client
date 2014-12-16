package org.esp.publisher;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.opengis.referencing.crs.CoordinateReferenceSystem;

import com.vividsolutions.jts.geom.Polygon;

/**
 * Base class for uploaded spatial data files metadata.
 * 
 * @author mauro.bartolomeoli@geo-solutions.it
 *
 */
public abstract class PublishedFileMetadata {
    private Double maxVal;
    
    private File file;

    public Double getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(Double maxVal) {
        this.maxVal = maxVal;
    }

    private Polygon envelope;

    public Polygon getEnvelope() {
        return envelope;
    }

    public void setEnvelope(Polygon envelope) {
        this.envelope = envelope;
    }

    private String srid;

    public String getSrid() {
        return srid;
    }

    public void setSrid(String srid) {
        this.srid = srid;
    }

    private Double minVal;

    public Double getMinVal() {
        return minVal;
    }

    public void setMinVal(Double minVal) {
        this.minVal = minVal;
    }

    private CoordinateReferenceSystem crs;

    public CoordinateReferenceSystem getCrs() {
        return crs;
    }

    public void setCRS(CoordinateReferenceSystem crs) {
        this.crs = crs;
    }
    
    public String getDescription() {
        StringBuilder repr = new StringBuilder();
        appendDescription(repr);
        return repr.toString();
    }

    protected void appendDescription(StringBuilder repr) {
        repr.append("Spatial reference: ").append(getSrid());
        repr.append("\n");
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    String attributeName = null;
    
    Map<String, Class<?>> attributes = null;

    
    /**
     * Sets attribute name to use for simple themas.
     * 
     * @param attributeName
     */
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeName() {
        return attributeName;
    }
    
    /**
     * Sets list of available attributes.
     * 
     * @param attributes
     */
    public void setAttributes(Map<String, Class<?>> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Class<?>> getAttributes() {
        return attributes;
    }
     
}
