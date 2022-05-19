package com.example.demo.service.IMPL;

import com.example.demo.DTO.AccountDTO;
import com.example.demo.DTO.AccountPage;
import com.example.demo.DTO.RegisterRequest;
import com.example.demo.exception.AccountExeption;
import com.example.demo.exception.MailException;
import com.example.demo.exception.UsernameExitException;
import com.example.demo.map.AccountMap;
import com.example.demo.model.Account;
import com.example.demo.model.AccountRole;
import com.example.demo.model.Key.RoleForAccountKey;
import com.example.demo.model.RoleForAccount;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.AccountRoleRepository;
import com.example.demo.service.*;
import com.example.demo.util.AppConstants;
import com.example.demo.util.DataUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMap accountMap;
    private final PasswordEncoder passwordEncoder;
    private final RoleForAccountService roleForAccountService;
    private final AccountRoleRepository accountRoleRepository;
    private final SendMailService sendMailService;
    private final UserHistoryService userHistoryService;
    private final VerificationTokenService verificationTokenService;
    private final MovieEvaluateService movieEvaluateService;

    @Override
    public List<AccountDTO> getAllAccounts() {
        return accountMap.listAccountToListDTO(accountRepository.findAll());
    }

    @Override
    public List<AccountDTO> getAccountByEnabled(boolean check) {
        List<AccountDTO> accountDTOS = accountMap.listAccountToListDTO(accountRepository.findAll());
        List<AccountDTO> accountDTOCheck = new ArrayList<>();
        accountDTOS.forEach(accountDTO -> {
            if (accountDTO.isEnabled() == check) {
                accountDTOCheck.add(accountDTO);
            }
        });
        return accountDTOCheck;
    }

    @Override
    public List<AccountRole> getFullRoleOnAccount(Account account) {
        List<AccountRole> accountRoles = new ArrayList<>();
        account.getAccountRoles().forEach(role -> {
            accountRoles.add(role.getAccountRole());
        });
        return accountRoles;
    }

    @Override
    public AccountDTO getAccountById(int accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            throw new AccountExeption("Account not found");
        } else {
            return accountMap.accountToDTO(account);
        }
    }

    @Override
    public AccountDTO getAccountByUsernameDTO(String accountUsername) {

        Account account = accountRepository.findMovieAccountByUsername(accountUsername);
        if (account == null) {
            throw new AccountExeption("Account not found");
        } else {
            return accountMap.accountToDTO(account);
        }
    }

    @Override
    public Account getAccountByUsername(String accountUsername) {
        Account account = accountRepository.findMovieAccountByUsername(accountUsername);
        if (account == null) {
            throw new AccountExeption("Account not found");
        } else {
            return account;
        }
    }

    @Override
    public boolean checkPasswordForAccount(Account account, String currentPassword) {
        if (account == null) {
            throw new AccountExeption("Account not found");
        } else {
            return passwordEncoder.matches(currentPassword, account.getPassword());
        }
    }

    @Override
    public Boolean changePasswordForAccount(Account account, String passwordHadChange) {
        account.setPassword(passwordEncoder.encode(passwordHadChange));
        accountRepository.save(account);
        return true;
    }

    @Override
    public String deleteAccountByUsername(String username) {
        Account account = accountRepository.findMovieAccountByUsername(username);
        if (account == null) {
            throw new AccountExeption("Account not found with username: " + username);
        } else {
            int id = account.getId();
            userHistoryService.deleteUserHistoryFromAccount(id);
            verificationTokenService.deleteUserTokens(id);
            movieEvaluateService.deleteMovieEvaluateByUserId(id);
            roleForAccountService.deleteRole(id);
            accountRepository.delete(account);
            return "Remove Account Successfully";
        }
    }

    @Override
    public Account editAccountByUsername(AccountDTO accountDTO) throws MailException {
        Account account = accountRepository.findMovieAccountByUsername(accountDTO.getUsername());
        if (checkEmail(accountDTO.getEmail(), account.getUsername()) != false) {
            throw new MailException("Email is exit");
        } else {
            account.setLastname(accountDTO.getLastname());
            accountRepository.save(account);
            System.out.println("\n\n\n\n\n\n\n bbbbbbbbbbbbbbbbbbbbbb: " + account.getLastname());
            return account;
        }
    }

    @Override
    public RegisterRequest createAccount(RegisterRequest registerRequest, int roleId) throws
            MailException, UsernameExitException {
        //check mail bang fasle => email không tồn tại
        if (this.checkEmail(registerRequest.getEmail(), registerRequest.getUsername()) == false
                && this.checkUsername(registerRequest.getUsername()) == false) {
            Account account = new Account();
            account.setUsername(registerRequest.getUsername());
            account.setPassword(DataUtils.generateTempPwd(8));
            account.setEmail(registerRequest.getEmail());
            account.setLastname(registerRequest.getLastname());
            account.setFirstname(registerRequest.getFirstname());
            account.setBirthday(registerRequest.getBirthday());
            account.setGender(registerRequest.isGender());
            account.setAvatar(registerRequest.getAvatar());
            account.setIdTown(registerRequest.getIdTown());
            account.setAddress(registerRequest.getAddress());
            account.setEnabled(true);
            accountRepository.save(account);
            sendMailService.create(account, account.getPassword());
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountRepository.save(account);
            roleForAccountService.addRoleForAccount(
                    accountRepository.findMovieAccountByUsername(account.getUsername()),
                    accountRoleRepository.getById(AppConstants.DEFAULT_ROLE_KEY_USER));
            return registerRequest;
        }
        return null;
    }

    @Override
    public AccountPage getAllUsersPage(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
//        Pageable pageable = ;
        Page<Account> users = accountRepository.findAll(PageRequest.of(pageNo, pageSize, sort));
        // get content for page object
        List<Account> listOfPosts = users.getContent();

        List<AccountDTO> content = accountMap.listAccountToListDTO(listOfPosts);
        AccountPage accountPage = new AccountPage();
        accountPage.setAccountDTOS(content);
        accountPage.setPageNo(users.getNumber());
        accountPage.setPageSize(users.getSize());
        accountPage.setTotalElements(users.getTotalElements());
        accountPage.setTotalPages(users.getTotalPages());
        accountPage.setFirst(users.isFirst());
        accountPage.setLast(users.isLast());
        return accountPage;
    }

    @Override
    public boolean forgotPassword(Account account) {
        account.setPassword(DataUtils.generateTempPwd(8));
        sendMailService.forgotPassword(account, account.getPassword());
        account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(12)));
        accountRepository.save(account);
        return true;
    }

    @Override
    public Account getAccountByEmail(String email) {
        for (Account account : new ArrayList<>(accountRepository.findAll())) {
            if (email.equals(account.getEmail())) {
                return account;
            }
        }
        return null;
    }

    private boolean checkEmail(String email, String username) throws MailException {
        for (Account account : new ArrayList<>(accountRepository.findAll())) {
            if (!account.getUsername().equals(username) && email.equals(account.getEmail())) {
                throw new MailException("User with email: " + email + " already existed");
            }
        }
        return false;
    }

    private boolean checkUsername(String username) throws UsernameExitException {
        for (Account userCheck : new ArrayList<>(accountRepository.findAll())) {
            if (username.equals(userCheck.getUsername())) {
                throw new UsernameExitException("Username is exit");
            }
        }
        return false;
    }
    private List<RoleForAccount> setDefaultRole(int accId){
        List<RoleForAccount> roleForAccounts = new ArrayList<>();
        roleForAccounts.add(new RoleForAccount(
                new RoleForAccountKey(accId, AppConstants.DEFAULT_ROLE_KEY_USER),
                accountRepository.getById(accId),
                accountRoleRepository.getById(AppConstants.DEFAULT_ROLE_KEY_USER)
        ));
        return roleForAccounts;
    }
}
