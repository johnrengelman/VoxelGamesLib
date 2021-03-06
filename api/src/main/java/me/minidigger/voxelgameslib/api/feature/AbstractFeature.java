package me.minidigger.voxelgameslib.api.feature;

import com.google.gson.annotations.Expose;

import javax.annotation.Nonnull;

import me.minidigger.voxelgameslib.api.phase.Phase;

/**
 * Abstract implementation of Phase
 */
public abstract class AbstractFeature implements Feature {

    @Expose
    private String name;

    private Phase phase;

    /**
     * Sets the name of this feature to the name of the class
     */
    public AbstractFeature() {
        name = getClass().getName().replace(FeatureTypeAdapter.DEFAULT_PATH + ".", "");
    }

    @Nonnull
    @Override
    public Phase getPhase() {
        return phase;
    }

    @Override
    public void setPhase(@Nonnull Phase phase) {
        this.phase = phase;
    }

    @Nonnull
    @Override
    public String getName() {
        return name;
    }
}
