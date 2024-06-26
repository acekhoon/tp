package seedu.hirehub.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.hirehub.commons.core.LogsCenter;
import seedu.hirehub.commons.exceptions.DataLoadingException;
import seedu.hirehub.model.ReadOnlyAddressBook;
import seedu.hirehub.model.ReadOnlyUserPrefs;
import seedu.hirehub.model.UserPrefs;
import seedu.hirehub.model.application.UniqueApplicationList;
import seedu.hirehub.model.job.UniqueJobList;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private JobsStorage jobsStorage;
    private ApplicationStorage applicationStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage,
                          UserPrefsStorage userPrefsStorage, JobsStorage jobsStorage,
                          ApplicationStorage applicationStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.jobsStorage = jobsStorage;
        this.applicationStorage = applicationStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ JobList methods ==============================
    @Override
    public Path getJobsFilePath() {
        return jobsStorage.getJobsFilePath();
    }

    @Override
    public Optional<UniqueJobList> readJobList() throws DataLoadingException {
        return readJobList(jobsStorage.getJobsFilePath());
    }

    @Override
    public Optional<UniqueJobList> readJobList(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return jobsStorage.readJobList(filePath);
    }

    @Override
    public void saveJobList(UniqueJobList jobList) throws IOException {
        saveJobList(jobList, jobsStorage.getJobsFilePath());
    }

    @Override
    public void saveJobList(UniqueJobList jobList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        jobsStorage.saveJobList(jobList, filePath);
    }

    // ================ ApplicationList methods ==============================
    @Override
    public Path getApplicationFilePath() {
        return applicationStorage.getApplicationFilePath();
    }

    @Override
    public Optional<UniqueApplicationList> readApplicationList(UniqueJobList jobs,
                                                               ReadOnlyAddressBook people) throws DataLoadingException {
        return readApplicationList(jobs, people, getApplicationFilePath());
    }

    @Override
    public Optional<UniqueApplicationList> readApplicationList(UniqueJobList jobs,
                                                               ReadOnlyAddressBook people,
                                                               Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return applicationStorage.readApplicationList(jobs, people, filePath);
    }

    @Override
    public void saveApplicationList(UniqueApplicationList applicationList) throws IOException {
        saveApplicationList(applicationList, getApplicationFilePath());
    }

    @Override
    public void saveApplicationList(UniqueApplicationList applicationList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        applicationStorage.saveApplicationList(applicationList, filePath);
    }
}
