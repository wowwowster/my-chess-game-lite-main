#!/bin/bash

log() {
  echo "*** $(date +'%Y-%m-%d %H:%M:%S')" "$@"
}

log "Waiting SonarQube analysis"

retryCount=1
maxRetry=30

docker logs sonar 2>&1 | grep "Publish task results | status=SUCCESS"
status=$?
while [ "$status" -ne 0 ] && [ "$retryCount" -le "$maxRetry" ]; do
  log "SonarQube analysis is not finished yet - sleep and retry (""$retryCount""/""$maxRetry"")"
  retryCount=$((retryCount+1))
  sleep 5
  docker logs sonar 2>&1 | grep "Publish task results | status=SUCCESS"
  status=$?
done

if [ "$status" -ne 0 ]; then
  log "SonarQube analysis is not finished after" "$retryCount" " retries."
  return 1
fi

log "SonarQube analysis is finished"
