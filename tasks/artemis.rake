ARTEMIS_VERSION='a609b2076aacc0ef5ecf0b390205d01bb88ceae2'
ARTEMIS = struct(
  :framework => "com.artemis:artemis:jar:#{ARTEMIS_VERSION}",
  :framework_sources => "com.artemis:artemis:jar:sources:#{ARTEMIS_VERSION}"
)

artifact(ARTEMIS.framework).from(download("http://gamadu.com/artemis/artemis-#{ARTEMIS_VERSION}.jar"))
artifact(ARTEMIS.framework_sources).from(download("http://gamadu.com/artemis/artemis-#{ARTEMIS_VERSION}-src.jar"))
