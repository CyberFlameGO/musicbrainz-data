# musicbrainz-data

Java data bindings for the [MusicBrainz Database](http://musicbrainz.org/doc/MusicBrainz_Database) using Hibernate.

## Things to know

* As of now, `musicbrainz-data` is meant for read-only access to the MusicBrainz Database. Nothing, however, prevents a user from changing entites; any change might be written to the database and might leave it in an inconsistent state afterwards.
* Only a subset of the [database schema](http://musicbrainz.org/doc/MusicBrainz_Database/Schema) is implemented. Access is provided to artists, releases, tracks and recordings (and related tables).

## Configuration

### Set some properties for your context 

        db.musicbrainz.driver.class=org.postgresql.Driver
        db.musicbrainz.url=jdbc:postgresql://localhost:5432/musicbrainz_db
        db.musicbrainz.user=musicbrainz
        db.musicbrainz.password=musicbrainz

(see `src/main/config/musicbrainz-data.properties.example` for all available options)

### Import the musicbrainz-data Spring context:

        <import resource="spring/musicbrainz-data.xml" />

## Usage examples

### Query for all artists named 'MONO' (casing will be ignored)

        List<Artist> artists = artistDao.getByName("mono");

### Get release with GID 'c69b70bc-049e-3e3f-a5e4-5a1b4d62105f'

        UUID musicBrainzId = UUID.fromString("c69b70bc-049e-3e3f-a5e4-5a1b4d62105f");
        Release release = releaseDao.getByGid(musicBrainzId);

## License

Copyright 2012 [Last.fm](http://www.last.fm/)

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 
[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)
 
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

## Kthxbye

Last.fm <3 MusicBrainz
