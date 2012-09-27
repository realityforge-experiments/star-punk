ARTEMIS_VERSION='a609b2076aacc0ef5ecf0b390205d01bb88ceae2'
ARTEMIS = struct(
  :framework => "com.artemis:artemis:jar:#{ARTEMIS_VERSION}",
  :framework_sources => "com.artemis:artemis:jar:sources:#{ARTEMIS_VERSION}"
)

download(artifact(ARTEMIS.framework) => "http://gamadu.com/artemis/artemis-#{ARTEMIS_VERSION}.jar")
download(artifact(ARTEMIS.framework_sources) => "http://gamadu.com/artemis/artemis-#{ARTEMIS_VERSION}-src.jar")
