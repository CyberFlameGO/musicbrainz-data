/*
 * Copyright 2012 Last.fm
 * Copyright 2012 Aurélien Mino <aurelien.mino@gmail.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package fm.last.musicbrainz.data.dao.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fm.last.musicbrainz.data.AbstractHibernateModelIT;
import fm.last.musicbrainz.data.dao.ArtistDao;
import fm.last.musicbrainz.data.model.Artist;
import fm.last.musicbrainz.data.model.ArtistType;
import fm.last.musicbrainz.data.model.Gender;

public class ArtistDaoImplIT extends AbstractHibernateModelIT {

  @Autowired
  private ArtistDao dao;

  @Test
  public void getByExistingIdReturnsOneArtist() {
    Artist artist = dao.getById(1);
    assertThat(artist.getName(), is("Q and Not U"));
  }

  @Test
  public void getByNotExistingIdReturnsNull() {
    Artist artist = dao.getById(9001);
    assertThat(artist, is(nullValue()));
  }

  @Test
  public void getByExistingGidReturnsOneArtist() {
    UUID gid = UUID.fromString("994fcd41-2831-4318-9825-66bacbcf2cfe");
    Artist artist = dao.getByGid(gid);
    assertThat(artist.getName(), is("Q and Not U"));
  }

  @Test
  public void getByNotExistingGidReturnsNull() {
    UUID gid = UUID.fromString("b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d");
    Artist artist = dao.getByGid(gid);
    assertThat(artist, is(nullValue()));
  }

  @Test
  public void getByExistingRedirectedGidReturnsOneArtist() {
    UUID gid = UUID.fromString("a934e33f-b3cb-47dd-9638-f7f1f25fe162");
    Artist artist = dao.getByGid(gid);
    assertThat(artist.getName(), is("Q and Not U"));
  }

  @Test
  public void getByExistingGidReturnsOneArtistThatHasNoRedirectedGids() {
    UUID gid = UUID.fromString("194fcd41-2831-4318-9825-66bacbcf2cfe");
    Artist artist = dao.getByGid(gid);
    assertThat(artist.getName(), is("Mono"));
  }

  @Test
  public void getByExistingNameReturnsTwoArtists() {
    String artistName = "Mono";
    List<Artist> artists = dao.getByName(artistName);
    assertThat(artists, hasSize(2));
    for (Artist artist : artists) {
      assertThat(artist.getName(), is(equalToIgnoringCase(artistName)));
    }
  }

  @Test
  public void getByExistingUppercaseNameReturnsTwoArtists() {
    List<Artist> artists = dao.getByName("MONO");
    assertThat(artists, hasSize(2));
  }

  @Test
  public void getByExistingLowercaseNameReturnsTwoArtists() {
    List<Artist> artists = dao.getByName("mono");
    assertThat(artists, hasSize(2));
  }

  @Test
  public void getByNotExistingNameReturnsEmptyList() {
    List<Artist> artists = dao.getByName("does not exist");
    assertThat(artists, hasSize(0));
  }

  @Test
  public void testDefinedGender() {
    Artist artist = dao.getById(1);
    assertThat(artist.getGender(), is(Gender.MALE));
  }

  @Test
  public void testUndefinedGender() {
    Artist artist = dao.getById(2);
    assertThat(artist.getGender(), is(Gender.UNDEFINED));
  }

  @Test
  public void testDefinedType() {
    Artist artist = dao.getById(1);
    assertThat(artist.getType(), is(ArtistType.PERSON));
  }

  @Test
  public void testUndefinedType() {
    Artist artist = dao.getById(2);
    assertThat(artist.getType(), is(ArtistType.UNDEFINED));
  }

}
