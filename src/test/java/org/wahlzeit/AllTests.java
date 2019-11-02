/*
 * Copyright (c) 2019 by Dennis Possart, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.handlers.TellFriendTest;
import org.wahlzeit.model.AccessRightsTest;
import org.wahlzeit.model.FlagReasonTest;
import org.wahlzeit.model.GenderTest;
import org.wahlzeit.model.GuestTest;
import org.wahlzeit.model.PhotoFilterTest;
import org.wahlzeit.model.TagsTest;
import org.wahlzeit.model.UserStatusTest;
import org.wahlzeit.model.ValueTest;
import org.wahlzeit.model.persistence.AbstractAdapterTest;
import org.wahlzeit.model.persistence.DatastoreAdapterTest;
import org.wahlzeit.services.EmailAddressTest;
import org.wahlzeit.services.LogBuilderTest;
import org.wahlzeit.services.mailing.EmailServiceTestSuite;
import org.wahlzeit.utils.StringUtilTest;
import org.wahlzeit.utils.VersionTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		// package handlers
		TellFriendTest.class,
		// package model
		AccessRightsTest.class, FlagReasonTest.class, GenderTest.class, GuestTest.class, PhotoFilterTest.class,
		TagsTest.class, UserStatusTest.class, ValueTest.class,
		// package model.persistence
		DatastoreAdapterTest.class,
		// package services
		EmailAddressTest.class, LogBuilderTest.class,
		// package services.mailing
		// use the already existing TestSuite there
		EmailServiceTestSuite.class,
		// package utils
		StringUtilTest.class, VersionTest.class })

public class AllTests {

}