/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2018 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package org.training.service.impl;

import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.email.impl.DefaultEmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailAttachmentModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.util.List;


/**
 *
 */
public class MyDefaultEmailService extends DefaultEmailService
{
	private EmailService emailservice;
	private ConfigurationService configurationService;

	/**
	 * @return the emailservice
	 */
	public EmailService getEmailservice()
	{
		return emailservice;
	}

	/**
	 * @param emailservice
	 *           the emailservice to set
	 */

	public void setEmailservice(final EmailService emailservice)
	{
		this.emailservice = emailservice;
	}

	/**
	 * @return the configurationService
	 */
	@Override
	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	/**
	 * @param configurationService
	 *           the configurationService to set
	 */
	@Override
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}



	@Override
	public EmailMessageModel createEmailMessage(final List<EmailAddressModel> toAddresses, // NOSONAR
			final List<EmailAddressModel> ccAddresses, final List<EmailAddressModel> bccAddresses,
			final EmailAddressModel fromAddress, final String replyToAddress, final String subject, final String body,
			final List<EmailAttachmentModel> attachments)
	{
		System.out.println("inside MyDefaultEmailService "); //NOPMD
		final String bccEmailAddress = configurationService.getConfiguration().getProperty("custom.emailservice.bcc.address")
				.toString();
		bccAddresses.add(getEmailservice().getOrCreateEmailAddressForEmail(bccEmailAddress, bccEmailAddress));
		return super.createEmailMessage(toAddresses, ccAddresses, bccAddresses, fromAddress, replyToAddress, subject, body,
				attachments);
	}



}
