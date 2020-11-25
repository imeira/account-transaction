CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `document_number` varchar(255) NOT NULL
);

CREATE TABLE `operation_type` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL
);

CREATE TABLE `transaction` (
  `id` bigint(20) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  `operation_type_id` bigint(20) NOT NULL,
  `amount` decimal(19,2) DEFAULT 0,
  `event_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--
-- Indexes for table `empresa`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `operation_type`
--
ALTER TABLE `operation_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `operation_type`
--
ALTER TABLE `operation_type`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

-- Constraints for dumped tables
--

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `account_fk` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `operation_type_fk` FOREIGN KEY (`operation_type_id`) REFERENCES `operation_type` (`id`);
