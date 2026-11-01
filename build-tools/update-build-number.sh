#!/usr/bin/env sh
set -eu

properties_path=${1:?Missing properties file path}
build_session=${2:?Missing build session timestamp}
properties_dir=$(dirname "$properties_path")
mkdir -p "$properties_dir"

current_year=$(date +%y)
current_count=0

if [ -f "$properties_path" ]; then
    old_year=$(sed -n 's/^build.year=//p' "$properties_path" | tail -n 1)
    old_count=$(sed -n 's/^build.count=//p' "$properties_path" | tail -n 1)
    old_session=$(sed -n 's/^build.session=//p' "$properties_path" | tail -n 1)

    if [ "$old_year" = "$current_year" ] && [ "$old_session" = "$build_session" ]; then
        exit 0
    fi

    if [ "$old_year" = "$current_year" ] && [ -n "$old_count" ]; then
        # Strip leading zeroes so POSIX shell arithmetic never treats the value as octal.
        old_count=$(printf '%s' "$old_count" | sed 's/^0*//')
        if [ -n "$old_count" ]; then
            case "$old_count" in
                *[!0-9]*) old_count=0 ;;
            esac
            current_count=$old_count
        fi
    fi
fi

next_count=$((current_count + 1))
build_count=$(printf '%02d' "$next_count")
short_build_number=$((current_year * 100 + next_count))
temporary_path="${properties_path}.tmp"

{
    printf '%s\n' '# Auto-generated build number for SlimefunCore.'
    printf 'build.year=%s\n' "$current_year"
    printf 'build.count=%s\n' "$build_count"
    printf 'short.build.number=%s\n' "$short_build_number"
    printf 'generated.short.build.number=%s\n' "$short_build_number"
    printf 'build.session=%s\n' "$build_session"
} > "$temporary_path"

mv "$temporary_path" "$properties_path"
