package io.github.thebusybiscuit.slimefun4.core.services;

import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestWorldBackupService {

    @Test
    @DisplayName("World backup ZIP entries are namespaced by world folder")
    void testWorldRootFilesDoNotCollide() {
        Path overworld = WorldBackupService.zipEntryPath(
            Path.of("world"),
            Path.of("world", "paper-world.yml")
        );
        Path nether = WorldBackupService.zipEntryPath(
            Path.of("world_nether"),
            Path.of("world_nether", "paper-world.yml")
        );
        Path end = WorldBackupService.zipEntryPath(
            Path.of("world_the_end"),
            Path.of("world_the_end", "paper-world.yml")
        );

        Assertions.assertEquals(Path.of("world", "paper-world.yml"), overworld);
        Assertions.assertEquals(Path.of("world_nether", "paper-world.yml"), nether);
        Assertions.assertEquals(Path.of("world_the_end", "paper-world.yml"), end);
        Assertions.assertNotEquals(overworld, nether);
        Assertions.assertNotEquals(overworld, end);
        Assertions.assertNotEquals(nether, end);
    }

    @Test
    @DisplayName("Nested world backup files retain their relative path")
    void testNestedFilesRetainTheirRelativePath() {
        Path entry = WorldBackupService.zipEntryPath(
            Path.of("world_nether"),
            Path.of("world_nether", "DIM-1", "region", "r.0.0.mca")
        );

        Assertions.assertEquals(
            Path.of("world_nether", "DIM-1", "region", "r.0.0.mca"),
            entry
        );
    }
}
