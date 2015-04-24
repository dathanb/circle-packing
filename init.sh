#!/bin/sh


# ========================================
# Ensure failures cause immediate failures
# ========================================
set -e

# ==================================
# Remove template license and readme
# ==================================
rm -f LICENSE README.md

# ================================
# Initialize project Gradle config
# ================================
./gradlew -b bootstrap.gradle

# ======================
# Cleanup template stuff
# ======================
rm bootstrap.gradle
rm -rf templates
rm -rf files

# ============================================
# Clear out the Gradle Template git repository
# ============================================
rm -rf .git

# ========================
# Build new git repository
# ========================
git init .
git co -b develop
git add .
git commit -m "Started from Gradle Template"
git branch master

# ===========================
# Starting the semver tagging
# ===========================
git tag v0.1.0-dev

# =================================================
# Remove this script, but don't worry about failure
# =================================================
set +e
rm init.sh
set -e
