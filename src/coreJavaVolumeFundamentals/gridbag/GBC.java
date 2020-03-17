package coreJavaVolumeFundamentals.gridbag;

import java.awt.*;

/**
 * @author zhangjinglong
 * @date 2020-03-14-9:36 下午
 * <p>
 * This class simplifies the use of GridBagConstraints class.
 */

public class GBC extends GridBagConstraints {

    /**
     * Constructs a GBC with a given gridx and gridy postion and all other grid
     *
     * @param gridx the gridx position
     * @param gridy the gridy postion
     */
    public GBC(int gridx, int gridy) {
        this.gridy = gridy;
        this.gridx = gridx;
    }


    /**
     * Constructs a GBC with given gridx,gridy,gridwidth,gridheight and all
     *
     * @param gridx      the gridx position
     * @param gridy      the gridy postion
     * @param gridwidth  the cell span in x-direction
     * @param gridheight the cell span in y-direction
     */
    public GBC(int gridx, int gridy, int gridwidth, int gridheight) {
        this(gridx, gridy);
        this.gridheight = gridheight;
        this.gridwidth = gridwidth;

    }

    /**
     * Sets the anchor
     *
     * @param anchor the anchor value
     * @return this object for futher modification
     */
    public GBC setAnchor(int anchor) {
        this.anchor = anchor;
        return this;
    }

    /**
     * Sets the fill direction
     *
     * @param fill the fill direction
     * @return this object for further modification
     */
    public GBC setFill(int fill) {
        this.fill = fill;
        return this;
    }


    /**
     * Sets the cell weights
     *
     * @param weightx the cell weight in x-direction
     * @param weighty the cell weight in y-direction
     * @return
     */
    public GBC setWeight(double weightx, double weighty) {
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }

    /**
     * Sets the insets of this cell
     *
     * @param distance the spacing to use inf all directions
     * @return this object for further modification
     */
    public GBC setInsets(int distance) {
        this.insets = new Insets(distance, distance, distance, distance);
        return this;
    }

    /**
     * Sets the insets of this cell
     *
     * @param top
     * @param left
     * @param botton
     * @param right
     * @return this object for further modification
     */
    public GBC setInsets(int top, int left, int botton, int right) {
        this.insets = new Insets(top, left, botton, right);
        return this;
    }

    /**
     * Sets the internal padding
     *
     * @param ipadx the internal padding in x-direction
     * @param ipady the internal padding in y-direction
     * @return this object for further modification
     */
    public GBC setIpad(int ipadx, int ipady) {
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }


}
